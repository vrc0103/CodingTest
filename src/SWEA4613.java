import java.io.*;
import java.util.*;

public class SWEA4613 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nTestCase;
    static int nRow, nCol;
    static int nRes;
    static int[][] nColors;
    static char cColor;
    static char[][] cFlag;
    static String sInput;

    static void setFlag() throws IOException {
        cFlag = new char[nRow][nCol]; // 깃발 초기화
        nColors = new int[nRow][3]; // 각 행별 색깔 수

        for (int i = 0; i < nRow; i++) {
            sInput = br.readLine().trim();
            for (int j = 0; j < nCol; j++) {
                cColor = sInput.charAt(j);
                cFlag[i][j] = cColor;
                if (cColor == 'W') {
                    nColors[i][0]++;
                } else if (cColor == 'B') {
                    nColors[i][1]++;
                } else {
                    nColors[i][2]++;
                }
            }
        }
    }

    static void countColors() {
        int nW, nB, nR;
        int nTmp1, nTmp2;

        for (nW = 0; nW < nRow - 2; nW++) { // W가 차지하는 행 수
            nTmp1 = 0;
            for (int i = 0; i <= nW; i++) { // W로 칠해야할 칸 수
                nTmp1 += nColors[i][1] + nColors[i][2];
            }

            for (nB = nW + 1; nB < nRow - 1; nB++) { // B가 차지하는 행 수
                nTmp2 = 0;
                for (int j = nW + 1; j <= nB; j++) { // B로 칠해야할 칸 수
                    nTmp2 += nColors[j][0] + nColors[j][2];
                }

                for (nR = nB + 1; nR < nRow; nR++) { // R로 칠해야할 칸 수
                    nTmp2 += nColors[nR][0] + nColors[nR][1];
                }
                nRes = Math.min(nRes, nTmp1 + nTmp2);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            nRow = Integer.parseInt(st.nextToken());
            nCol = Integer.parseInt(st.nextToken());
            nRes = nRow * nCol;

            setFlag();

            countColors();

            sb.append(String.format("#%d %d\n", tc, nRes));
        }

        System.out.print(sb);
    }
}
