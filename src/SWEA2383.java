/*
 * 계단 입구까지 이동 : Row 길이 + Col 길이
 * 계단을 통해 아래로 : 계단 길이만큼 시간 소요, 계단 이용은 최대 3명까지
 * 모든 사람이 이동 완료하는 시간의 최솟값 출력
 */

import java.io.*;
import java.util.*;

public class SWEA2383 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int size;
    static int res;
    static int[][] map;

    static int people; // 사람 수
    static int[] select; // idx번째 사람이 선택한 계단 번호
    static int[][] stairLoc; // 계단 위치
    static int[][] stairLen; // 각 사람 별 계단까지 남은 거리

    public static void main(String[] args) throws IOException {
        int stairIdx, peopleIdx;

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            stairIdx = 0;
            people = 0;
            res = Integer.MAX_VALUE;

            stairLoc = new int[2][2];

            // 지도 초기화
            size = Integer.parseInt(br.readLine().trim());
            map = new int[size][size];

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine().trim());

                for (int j = 0; j < size; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    // 사람 수 체크
                    if (map[i][j] == 1) {
                        people++;
                    }

                    // 계단 위치 파악
                    if (map[i][j] > 1) {
                        stairLoc[stairIdx][0] = i;
                        stairLoc[stairIdx][1] = j;
                        stairIdx++;
                    }
                }
            }

            // 각 사람 별 계단까지 거리 계산
            peopleIdx = 0;
            stairLen = new int[people][2];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][j] == 1) {
                        stairLen[peopleIdx][0] = Math.abs(i - stairLoc[0][0]) + Math.abs(j - stairLoc[0][1]);
                        stairLen[peopleIdx][1] = Math.abs(i - stairLoc[1][0]) + Math.abs(j - stairLoc[1][1]);
                        peopleIdx++;
                    }
                }
            }

            select = new int[people];
            countTime(0);

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb);
    }

    static void countTime(int peopleCnt) {
        if (peopleCnt == people) { // 모든 사람이 계단을 선택한 경우 필요한 시간을 계산하고 재귀 종료
            getMinTime();

            return;
        }

        select[peopleCnt] = 0;
        countTime(peopleCnt + 1);

        select[peopleCnt] = 1;
        countTime(peopleCnt + 1);
    }

    static void getMinTime() { // 각 사람이 어느 계단을 이용할 지 모두 선택한 상태
        int timeTmp;
        int cntTime1 = 0;
        int cntTime2 = 0;
        int stair1Len = map[stairLoc[0][0]][stairLoc[0][1]];
        int stair2Len = map[stairLoc[1][0]][stairLoc[1][1]];

        ArrayList<Integer> ppLen1 = new ArrayList<>(); // 계단까지 필요한 거리
        ArrayList<Integer> ppLen2 = new ArrayList<>();

        // 각 계단을 선택한 사람별로 시간순 정렬
        for (int i = 0; i < people; i++) {
            if (select[i] == 0) {
                ppLen1.add(stairLen[i][0]);
            } else {
                ppLen2.add(stairLen[i][1]);
            }
        }

        Collections.sort(ppLen1);
        Collections.sort(ppLen2);

        cntTime1 = calcTime(ppLen1, stair1Len);
        cntTime2 = calcTime(ppLen2, stair2Len);

        timeTmp = Math.max(cntTime1, cntTime2); // 모든 사람이 최종적으로 이용을 마치는 시간

        res = Math.min(res, timeTmp); // 더 작은 값으로 갱신
    }

    static int calcTime(ArrayList<Integer> ppLen, int stairLen) {
        int cntTime = 0;
        ArrayList<Integer> pastState = new ArrayList<>(); // 이전 상태 저장

        while (!ppLen.isEmpty()) {
            cntTime++;

            pastState.clear();
            for (int i = 0; i < ppLen.size(); i++) { // 이전 상태 저장해둠
                pastState.add(ppLen.get(i));
            }

            for (int i = 0; i < ppLen.size(); i++) {
                if (i < 3) {
                    // 세 번째 사람까지는 계단 이용
                    ppLen.set(i, ppLen.get(i) - 1);
                } else {
                    // 네 번째 사람부터는 계단에서 대기
                    ppLen.set(i, Math.max(ppLen.get(i) - 1, 0));
                }
            }

            while (!ppLen.isEmpty()) {
                if (ppLen.get(0) == (stairLen + 1) * -1) { // 계단 다 내려간 경우 리스트에서 삭제
                    ppLen.remove(0);
                    pastState.remove(0);

                    // 막 도착한 사람들 말고 기다리고 있던 사람들은 한명 들여보내줘야 함
                    if (ppLen.size() > 2 && pastState.get(2) == 0) {
                        ppLen.set(2, ppLen.get(2) - 1);
                        Collections.sort(ppLen);
                    }
                } else {
                    break;
                }
            }
        }

        return cntTime;
    }
}
