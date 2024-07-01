/*
 * "월 일 월 일"을 입력받아 두 날짜간 차이를 계산
 */

import java.io.*;
import java.util.*;

public class SWEA1948 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nRes;
        int[] nMDArr = new int[4];
        int[] nDayPerMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 각 월별 일수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nRes = 0;
            st = new StringTokenizer(br.readLine().trim());

            for (int i = 0; i < 4; i++) { // nMDARR 배열에 월 일 월 일 저장
                nMDArr[i] = Integer.parseInt(st.nextToken());
            }

            if (nMDArr[0] == nMDArr[2]) { // 시작, 종료 월이 동일하면 일수만 계산
                nRes = nMDArr[3] - nMDArr[1] + 1;
            } else {
                nRes = nDayPerMonth[nMDArr[0]] - nMDArr[1] + 1 + nMDArr[3]; // 시작 월의 남은 일수 + 종료 월의 남은 일수
                for (int nMonth = nMDArr[0] + 1; nMonth < nMDArr[2]; nMonth++) {
                    nRes += nDayPerMonth[nMonth];
                }
            }

            System.out.println("#" + test + " " + nRes);
        }
    }
}
