import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SWEA6190 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int nTestCase;
    public static int nNum;
    public static int[] nInput;
    public static int[] nCross;

    public static void setCross() {
        int idx = 0;

        for (int i = 0; i < nNum - 1; i++) {
            for (int j = i + 1; j < nNum; j++) {
                nCross[idx++] = nInput[i] * nInput[j];
            }
        }
    }

    public static void getIncrease() {
        int nLen;
        String sTmp;

        for (int i = 0; i < nCross.length; i++) {
            sTmp = Integer.toString(nCross[i]);
            nLen = sTmp.length();
            if (nLen > 1) { // 숫자의 길이가 2 이상이어야 증감 판단 가능
                for (int j = 0; j < nLen - 1; j++) { // 다음 자릿수에서 감소하면 -1저장, 탈출
                    if (sTmp.charAt(j) > sTmp.charAt(j + 1)) {
                        nCross[i] = -1;
                        break;
                    }
                }
            }
        }

        Arrays.sort(nCross);
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nTestCase; tc++) {
            nNum = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());

            nInput = new int[nNum];
            for (int i = 0; i < nNum; i++) {
                nInput[i] = Integer.parseInt(st.nextToken());
            }

            nCross = new int[nNum * (nNum - 1) / 2];
            setCross();
            getIncrease();

            sb.append(String.format("#%d %d\n", tc, nCross[nCross.length - 1]));
        }

        System.out.print(sb);
    }
}
