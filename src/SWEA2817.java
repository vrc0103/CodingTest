/*
 * 1개 이상의 수를 선택하여 원하는 숫자가 되는 경우의 수
 */

import java.io.*;
import java.util.*;

public class SWEA2817 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nTestCase;
    static int nArrLen;
    static int nNum;
    static int nRes;
    static Integer[] nArr;

    static void getArr() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nArrLen = Integer.parseInt(st.nextToken());
        nNum = Integer.parseInt(st.nextToken());

        nArr = new Integer[nArrLen];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < nArrLen; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nArr, Comparator.reverseOrder());
    }

    static void countNum(int nSum, int nIdx) {
        if (nSum + nArr[nIdx] > nNum) { // 해당 인덱스의 배열값을 더했을 때 목표 숫자보다 커지면 재귀 탈출
            return;
        }

        nSum += nArr[nIdx];

        if (nSum == nNum) { // 더한 값이 목표 숫자이면 결과 1증가
            nRes++;
        } else { // 목표 숫자보다 작으면 배열의 뒷 값들을 확인하며 계산
            for (int i = 1; i < nArrLen - nIdx; i++) {
                countNum(nSum, nIdx + i);
            }
        }
    }

    static void getRes() {
        for (int i = 0; i < nArrLen; i++) {
            countNum(0, i);
        }
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nTestCase; tc++) {
            getArr(); // 입력받은 숫자들을 배열에 저장하고 정렬

            nRes = 0;
            getRes();

            sb.append(String.format("#%d %d\n", tc, nRes));
        }

        System.out.print(sb);
    }
}