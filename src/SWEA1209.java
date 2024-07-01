import java.io.*;
import java.util.*;

public class SWEA1209 {
    public static void main(String[] args) throws IOException {
        int nMax;
        int nSumRow, nSumCol, nRDiag, nLDiag;
        int[][] nTwoArr = new int[100][100];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        for (int test = 1; test <= 10; test++) {
            nMax = 0;
            nLDiag = 0;
            nRDiag = 0;

            sb = new StringBuilder();
            sb.append("#").append(br.readLine().trim()).append(" ");

            for (int i = 0; i < 100; i++) { // 배열 저장
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < 100; j++) {
                    nTwoArr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 100; i++) { // 각 행, 열별로 더해서 최댓값 갱신
                nSumRow = 0;
                nSumCol = 0;

                for (int j = 0; j < 100; j++) {
                    nSumRow += nTwoArr[i][j];
                    nSumCol += nTwoArr[j][i];
                }
                nMax = Math.max(nMax, Math.max(nSumRow, nSumCol));
            }

            for (int i = 0; i < 100; i++) { // 각 대각선의 합으로 최댓값 비교
                nLDiag += nTwoArr[i][99 - i];
                nRDiag += nTwoArr[i][i];
            }
            nMax = Math.max(nMax, Math.max(nLDiag, nRDiag));

            sb.append(nMax);
            System.out.println(sb);
        }
    }
}
