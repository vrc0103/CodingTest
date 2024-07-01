/*
 * 첫 줄에 테스트케이스
 * 손님 N명, M시간동안 K개의 붕어빵
 * 손님 오는 시간
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SWEA1860 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static String BreadCheck(int[] nCstmrTime, int[] nBreadTime, int nTime) {
        // 손님이 도착한 시간 전까지 남아있는 붕어빵의 수 모두 더해서 0이면 판매 불가
        int nSum;
        String bPsble = "Possible";

        if (nTime > nCstmrTime[0]) { // 처음 붕어빵이 나오기 전에 손님이 먼저 오면 바로 imposible
            bPsble = "Impossible";
        } else {
            for (int i = 0; i < nCstmrTime.length; i++) {
                nSum = 0;

                for (int j = 0; j <= nCstmrTime[i]; j++) { // 손님 도착시간까지 남아있는 붕어빵 수
                    nSum += nBreadTime[j];
                }
                if (nSum > 0) { // 팔 수 있으면 배열의 0번 인덱스의 값을 1 감소시킴 -> 총합으로 판단하므로 계산에만 영향
                    nBreadTime[0]--;
                } else { // 팔 수 없는 경우 false로 판단하고 반복문 중단
                    bPsble = "Impossible";
                    break;
                }
            }
        }

        return bPsble;
    }

    public static void main(String[] args) throws IOException {
        int nTestCase;
        int nCstmr, nTime, nBread; // 손님수, 시간, 붕어빵 수
        int nCycle; // 붕어빵 만든 횟수
        int[] nCstmrTime; // 손님이 도착한 시간 정보
        int[] nBreadTime; // 붕어빵 계산

        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            sb = new StringBuilder();
            nCycle = 1;

            st = new StringTokenizer(br.readLine().trim());
            nCstmr = Integer.parseInt(st.nextToken());
            nTime = Integer.parseInt(st.nextToken());
            nBread = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine().trim());
            nCstmrTime = new int[nCstmr];
            for (int i = 0; i < nCstmr; i++) { // 손님이 도착한 시간 정보
                nCstmrTime[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(nCstmrTime); // 손님 도착 시간순으로 정렬

            nBreadTime = new int[nCstmrTime[nCstmr - 1] + 1]; // 마지막 손님이 도착하는 시간으로 배열 크기 결정
            while (nTime * nCycle < nBreadTime.length) { // 붕어빵이 만들어지는 시간, 갯수
                nBreadTime[nTime * nCycle] = nBread;
                nCycle++;
            }

            sb.append(String.format("#%d %s", tc, BreadCheck(nCstmrTime, nBreadTime, nTime)));
            System.out.println(sb);
        }
    }
}
