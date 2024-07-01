import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SWEA2805 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;

    public static int nTestCase;
    public static int nFarmSize;
    public static int nRes;
    public static int[][] nFarmGain;
    public static String sInput;

    public static void getFarmGain() {
        int nLen = 0;
        int nMid = nFarmSize / 2;
        int nRow = 0;

        while (nRow < nMid) {
            for (int i = nMid - nLen; i <= nMid + nLen; i++) {
                nRes += nFarmGain[nRow][i];
            }
            nLen++;
            nRow++;
        }
        while (nRow < nFarmSize) {
            for (int i = nMid - nLen; i <= nMid + nLen; i++) {
                nRes += nFarmGain[nRow][i];
            }
            nLen--;
            nRow++;
        }
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            nRes = 0;
            sb = new StringBuilder();

            nFarmSize = Integer.parseInt(br.readLine().trim());
            nFarmGain = new int[nFarmSize][nFarmSize];

            for (int i = 0; i < nFarmSize; i++) { // 농장 배열 입력
                sInput = br.readLine().trim();
                for (int j = 0; j < nFarmSize; j++) {
                    nFarmGain[i][j] = sInput.charAt(j) - '0';
                }
            }

            getFarmGain();
            sb.append(String.format("#%d %d", tc, nRes));
            System.out.println(sb);
        }
    }
}
