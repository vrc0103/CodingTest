import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int size;
    static int countMax;
    static int[][] cafeArr;

    static void countDessert(int locRow, int locCol, int len1, int len2, int rot, ArrayList<Integer> cafe) {
        // loc : 현재 위치 , len : 이동 거리(대각선 \ / 길이) , rot : 방향 전환 횟수 , cafe : 먹어본 디저트 종류
        if (len1 == 0 && len2 == 0) { // 사각형이 완성되면 최댓값을 비교하고 재귀 종료
            countMax = Math.max(cafe.size(), countMax);
            return;
        }

        if (locRow < 0 || locCol < 0 || locRow == size || locCol == size) { // 카페 지역을 벗어나는 경우
            return;
        }

        for (int i = 0; i < cafe.size(); i++) { // 먹어본 디저트를 파는 카페
            if (cafe.get(i) == cafeArr[locRow][locCol]) {
                return;
            }
        }

        cafe.add(cafeArr[locRow][locCol]); // 현재 카페 기록

        switch (rot) {
            case 0: // 오른쪽 아래로 진행중
                countDessert(locRow + 1, locCol + 1, len1 + 1, len2, rot, cafe); // 동일 방향으로 진행
                countDessert(locRow + 1, locCol - 1, len1, len2 + 1, rot + 1, cafe); // 회전
                break;
            case 1: // 왼쪽 아래로 진행중
                countDessert(locRow + 1, locCol - 1, len1, len2 + 1, rot, cafe); // 동일 방향으로 진행
                countDessert(locRow - 1, locCol - 1, len1 - 1, len2, rot + 1, cafe); // 회전
                break;
            case 2: // 왼쪽 위로 복귀
                if (len1 > 0) {
                    countDessert(locRow - 1, locCol - 1, len1 - 1, len2, rot, cafe);
                } else {
                    countDessert(locRow - 1, locCol + 1, len1, len2 - 1, rot + 1, cafe); // 대각선 \ 길이만큼 모두 움직인 경우 회전
                }
                break;
            case 3: // 오른쪽 위로 복귀
                countDessert(locRow - 1, locCol + 1, len1, len2 - 1, rot, cafe);
                break;
        }

        cafe.remove(cafe.size() - 1); // 스위치문 탈출 = 카페 탐방 진행 중단 -> 현재 카페 삭제
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> cafe;

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            countMax = -1;

            // 카페 배열 초기화
            size = Integer.parseInt(br.readLine());
            cafeArr = new int[size][size];

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine().trim());

                for (int j = 0; j < size; j++) {
                    cafeArr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 디저트 수 계산
            for (int row = 0; row < size - 2; row++) {
                for (int col = 1; col < size - 1; col++) {
                    cafe = new ArrayList<>();

                    cafe.add(cafeArr[row][col]); // 출발 카페 기록
                    countDessert(row + 1, col + 1, 1, 0, 0, cafe);
                }
            }

            sb.append(String.format("#%d %d\n", tc, countMax));
        }

        System.out.print(sb);
    }
}