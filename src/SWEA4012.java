/*
 * 재료의 갯수
 * 시너지 배열
 */

import java.io.*;
import java.util.*;

public class SWEA4012 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int numOfMat;
    static int minValue;
    static int[][] synArr; // 시너지 배열
    static boolean[] select;

    static void getSynArr() throws IOException { // 시너지 배열 초기화
        numOfMat = Integer.parseInt(br.readLine().trim());

        synArr = new int[numOfMat][numOfMat];

        for (int i = 0; i < numOfMat; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int j = 0; j < numOfMat; j++) {
                synArr[i][j] = Integer.parseInt(st.nextToken());
                minValue += synArr[i][j];
            }
        }
    }

    static void getValue() {
        int val = 0;

        for (int i = 0; i < numOfMat; i++) {
            for (int j = 0; j < numOfMat; j++) {
                if (select[i] && select[j]) { // 두 재료 모두 조합1에 포함
                    val += synArr[i][j];
                }

                if ((!select[i]) && (!select[j])) { // 두 재료 모두 조합2에 포함
                    val -= synArr[i][j];
                }
            }
        }

        minValue = Math.min(minValue, Math.abs(val));
    }

    static void getCombination(int start, int count) {
        if (count == numOfMat / 2) { // 재료의 절반을 선택한 경우
            getValue();

            return;
        }

        for (int i = start; i < numOfMat; i++) {
            if (!select[i]) { // 방문하지 않은 인덱스는
                select[i] = true; // 방문 배열에 체크하고
                getCombination(i + 1, count + 1); // 시작 인덱스를 1 증가시키고, count를 1 증가시키며 재귀 호출
                select[i] = false; // 다음 체크를 위해 false로 돌려둠
            }
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            getSynArr();

            select = new boolean[numOfMat];
            getCombination(0, 0); // 가능한 재료의 조합 탐색

            sb.append(String.format("#%d %d\n", tc, minValue));
        }

        System.out.print(sb);
    }
}
