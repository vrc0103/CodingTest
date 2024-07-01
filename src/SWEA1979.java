/*
 * 첫 줄에 테스트 케이스 갯수 T
 * 다음 줄에 퍼즐 크기 N, 찾을 단어의 길이 K
 * 다음 N줄에 퍼즐 모양(흰 부분이 1, 검은 부분이 0)
 */

import java.io.*;
import java.util.*;

public class SWEA1979 {
    public static int wordCheck(int[][] nPuz, int nN, int nK) {
        int nLen1, nLen2;
        int nCount = 0;

        for (int i = 0; i < nN; i++) {
            nLen1 = 0;
            nLen2 = 0;

            for (int j = 0; j < nN; j++) {
                if (nPuz[i][j] == 1) { // 각 행별 이어진 빈칸 길이 계산
                    nLen1++;
                    if (j == nN - 1 && nLen1 == nK) { // 마지막 행에 걸칠 경우 카운트 불가하므로 따로 계산해줌
                        nLen1 = 0;
                        nCount++;
                    }
                } else {
                    if (nLen1 == nK) { // 퍼즐 칸 연속이 끝났을 때 단어 길이와 같으면 nRow 증가
                        nCount++;
                    }
                    nLen1 = 0; // 빈칸 길이 0으로 초기화
                }

                if (nPuz[j][i] == 1) { // 각 열별 이어진 빈칸 길이 계산
                    nLen2++;
                    if (j == nN - 1 && nLen2 == nK) { // 마지막 열에 걸칠 경우 카운트 불가하므로 따로 계산해줌
                        nLen2 = 0;
                        nCount++;
                    }
                } else {
                    if (nLen2 == nK) { // 퍼즐 칸 연속이 끝났을 때 단어 길이와 같으면 nCol 증가
                        nCount++;
                    }
                    nLen2 = 0; // 빈칸 길이 0으로 초기화
                }
            }
        }
        return nCount;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int nN, nK;
        int[][] nPuz;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            st = new StringTokenizer(br.readLine().trim());
            nN = Integer.parseInt(st.nextToken());
            nK = Integer.parseInt(st.nextToken());
            nPuz = new int[nN][nN];

            for (int i = 0; i < nN; i++) { // 퍼즐 구현
                st = new StringTokenizer(br.readLine().trim());

                for (int j = 0; j < nN; j++) {
                    nPuz[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + test + " " + wordCheck(nPuz, nN, nK));
        }
    }
}
