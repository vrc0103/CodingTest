/*
 * 시 분 시 분 입력
 * 앞 시:분 에서 뒷 시:분 만큼 흐른 후의 시각 출력
 */

import java.io.*;
import java.util.*;

public class SWEA1976 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nH1, nM1, nH2, nM2, nHRes, nMRes;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            st = new StringTokenizer(br.readLine().trim());
            nH1 = Integer.parseInt(st.nextToken());
            nM1 = Integer.parseInt(st.nextToken());
            nH2 = Integer.parseInt(st.nextToken());
            nM2 = Integer.parseInt(st.nextToken());

            nHRes = nH1 + nH2;
            nMRes = nM1 + nM2;
            if (nMRes >= 60) { // 60분 넘어가면 시+1
                nMRes -= 60;
                nHRes++;
            }
            if (nHRes > 12) { // 12시 표기
                nHRes -= 12;
            }

            System.out.println("#" + test + " " + nHRes + " " + nMRes);
        }
    }
}
