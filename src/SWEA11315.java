/*
 * N x N 배열의 바둑판
 * 첫 줄에 케이스 수 T
 * 다음 줄부터 N과 돌의 위치 입력됨
 * 돌 있는 칸은 o , 없는 칸은 .
 */

import java.io.*;

public class SWEA11315 {
    static String arr5Judg(char[][] cArr) {
        int nR, nRD, nD, nLD;

        for (int i = 0; i < cArr.length; i++) {
            for (int j = 0; j < cArr.length; j++) {
                nR = 1;
                nRD = 1;
                nD = 1;
                nLD = 1;

                if (cArr[i][j] == 'o') { // 오목 판단 시작
                    for (int k = 1; k <= 5; k++) {
                        if (j + nR < cArr.length && cArr[i][j + nR] == 'o') { // 오른쪽
                            nR++;
                        } else {
                            nR = 1;
                        }
                        if (i + nRD < cArr.length && j + nRD < cArr.length && cArr[i + nRD][j + nRD] == 'o') { // 오른쪽 아래
                            nRD++;
                        } else {
                            nRD = 1;
                        }
                        if (i + nD < cArr.length && cArr[i + nD][j] == 'o') { // 아래
                            nD++;
                        } else {
                            nD = 1;
                        }
                        if (i + nLD < cArr.length && j - nLD >= 0 && cArr[i + nLD][j - nLD] == 'o') { // 왼쪽 아래
                            nLD++;
                        } else {
                            nLD = 1;
                        }
                        if (nR >= 5 || nRD >= 5 || nD >= 5 || nLD >= 5) {
                            return "YES";
                        }
                    }
                }
            }
        }
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sTmp;

        int nT;
        int nN;
        char[][] cArr;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 0; test < nT; test++) {
            sb = new StringBuilder();
            nN = Integer.parseInt(br.readLine().trim());
            cArr = new char[nN][nN];

            for (int i = 0; i < nN; i++) { // 바둑판 배열 저장
                sTmp = br.readLine().trim();

                for (int j = 0; j < nN; j++) {
                    cArr[i][j] = sTmp.charAt(j);
                }
            }

            sb.append('#').append(test + 1).append(' ').append(arr5Judg(cArr));
            System.out.println(sb);
        }
    }
}
