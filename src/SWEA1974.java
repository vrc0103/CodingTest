/*
 * 각 테스트 케이스별로 맞으면 1, 틀리면 0 출력
 */

import java.io.*;
import java.util.*;

public class SWEA1974 {
    public static int sudokuCheck(int[][] nSudoku) {
        int nSum;
        int nCheck = 1;

        for (int i = 0; i < 9; i++) { // 각 행, 열 별로 판단
            nSum = 0;
            for (int j = 0; j < 9; j++) { // 각 행의 총합
                nSum += nSudoku[i][j];
            }
            if (nSum != 45) { // 1+...+9와 다르면 0 반환
                nCheck = 0;
                break;
            }

            nSum = 0;
            for (int j = 0; j < 9; j++) { // 각 열의 총합
                nSum += nSudoku[j][i];
            }
            if (nSum != 45) { // 1+...+9와 다르면 0 반환
                nCheck = 0;
                break;
            }
        }

        if (nCheck == 1) { // 행, 열별 비교가 통과했을때만 추가 실행
            for (int i = 0; i < 3; i++) { // 3x3 구역 별로 판단
                for (int j = 0; j < 3; j++) {
                    nSum = 0;

                    for (int row = 0; row < 3; row++) {// 각 구역별 총합
                        for (int col = 0; col < 3; col++) {
                            nSum += nSudoku[3 * i + row][3 * j + col];
                        }
                    }

                    if (nSum != 45) { // 1+...+9와 다르면 0 반환
                        nCheck = 0;
                        break;
                    }
                }
            }
        }

        return nCheck;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int[][] nSudoku = new int[9][9];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            for (int i = 0; i < 9; i++) { // 입력받은 스도쿠를 배열에 저장
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < 9; j++) {
                    nSudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + test + " " + sudokuCheck(nSudoku));
        }
    }
}
