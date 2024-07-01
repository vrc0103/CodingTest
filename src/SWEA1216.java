/*
 * 100 x 100 배열에서 가장 긴 회문의 길이 찾기
 * 글자는 A B C로만 이루어짐
 * 가로, 세로 한 방향만 인정
 * 한 단어는 길이 1인 회문으로 판단
 * TC 번호와 회문판 입력됨
 */

import java.io.*;

public class SWEA1216 {
    public static StringBuilder sb;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int PlndrmLen(char[][] cPlndrm) {
        int nMax = 1;

        for (int i = 0; i < 100; i++) {

            for (int j = 0; j < 100; j++) {
                for (int k = 99; k >= j; k--) {
                    if (cPlndrm[i][j] == cPlndrm[i][k]) {// 각 행별로 뒤에서부터 동일한 글자 탐색
                        for (int rowidx = 0; rowidx < k - j + 1; rowidx++) {
                            if (cPlndrm[i][j + rowidx] != cPlndrm[i][k - rowidx]) {// 다른 글자가 있으면 중단
                                break;
                            }
                            if (rowidx == k - j && rowidx > nMax) { // 회문으로 판단 시 최댓값 갱신
                                nMax = k - j;
                            }
                        }
                    }
                    if (cPlndrm[j][i] == cPlndrm[k][i]) {// 각 열별로 뒤에서부터 동일한 글자 탐색
                        for (int colidx = 0; colidx < k - j + 1; colidx++) {
                            if (cPlndrm[j + colidx][i] != cPlndrm[k - colidx][i]) {// 다른 글자가 있으면 중단
                                break;
                            }
                            if (colidx == k - j && colidx > nMax) { // 회문으로 판단 시 최댓값 갱신
                                nMax = k - j;
                            }
                        }
                    }
                }
            }
        }

        return nMax + 1;
    }

    public static void main(String[] args) throws IOException {
        int nMaxLen;
        int nTestCase;
        char[][] cPlndrm = new char[100][100];

        for (int tc = 1; tc <= 10; tc++) {
            sb = new StringBuilder();

            nTestCase = Integer.parseInt(br.readLine().trim());

            for (int row = 0; row < 100; row++) { // 회문 배열 저장
                cPlndrm[row] = br.readLine().trim().toCharArray();
            }

            nMaxLen = PlndrmLen(cPlndrm);

            sb.append("#").append(nTestCase).append(" ").append(nMaxLen);
            System.out.println(sb);
        }
    }
}
