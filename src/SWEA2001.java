/*
 * 크기 N 행렬에 파리 존재, 각 칸별로 마릿수 지정
 * 크기 M 행렬 파리채로 파리 잡음
 * 이 때 잡을 수 있는 파리의 최댓값
 */

import java.io.*;
import java.util.*;

public class SWEA2001 {
    public static int countFly(int[][] nFly, int nM) {
        int nCount;
        int nRes = 0;

        for (int i = 0; i < nFly[0].length - nM + 1; i++) {
            for (int j = 0; j < nFly[0].length - nM + 1; j++) {
                nCount = 0;

                for (int row = 0; row < nM; row++) { // 잡을 수 있는 파리 수
                    for (int col = 0; col < nM; col++) {
                        nCount += nFly[i + row][j + col];
                    }
                }

                if (nRes < nCount) {
                    nRes = nCount;
                }
            }
        }

        return nRes;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int nN, nM;
        int[][] nFly;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            st = new StringTokenizer(br.readLine().trim());
            nN = Integer.parseInt(st.nextToken());
            nM = Integer.parseInt(st.nextToken());

            nFly = new int[nN][nN];
            for (int i = 0; i < nN; i++) { // 파리 입력을 배열에 저장
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < nN; j++) {
                    nFly[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + test + " " + countFly(nFly, nM));
        }
    }
}
