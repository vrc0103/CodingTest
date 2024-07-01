/*
 * 2차원 배열 크기 N
 * 90, 180, 270 회전한 배열 각각 출력
 */

import java.io.*;
import java.util.*;

public class SWEA1961 {
    public static void rotArr(int[][] nInput, int nN) {// 배열, 배열크기
        int[][] nArr90 = new int[nN][nN];
        int[][] nArr180 = new int[nN][nN];
        int[][] nArr270 = new int[nN][nN];

        for (int row = 0; row < nN; row++) { // 90
            for (int col = 0; col < nN; col++) {
                nArr90[row][col] = nInput[nN - col - 1][row];
            }
        }
        for (int row = 0; row < nN; row++) { // 180
            for (int col = 0; col < nN; col++) {
                nArr180[row][col] = nArr90[nN - col - 1][row];
            }
        }
        for (int row = 0; row < nN; row++) { // 270
            for (int col = 0; col < nN; col++) {
                nArr270[row][col] = nArr180[nN - col - 1][row];
            }
        }

        for (int row = 0; row < nN; row++) {
            for (int col = 0; col < nN; col++) {
                System.out.print(nArr90[row][col]);
            }
            System.out.print(" ");
            for (int col = 0; col < nN; col++) {
                System.out.print(nArr180[row][col]);
            }
            System.out.print(" ");
            for (int col = 0; col < nN; col++) {
                System.out.print(nArr270[row][col]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int nN;
        int[][] nInput;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nN = Integer.parseInt(br.readLine().trim());
            nInput = new int[nN][nN];

            for (int i = 0; i < nN; i++) { // 입력을 배열에 저장
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < nN; j++) {
                    nInput[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + test);
            rotArr(nInput, nN);
        }
    }
}
