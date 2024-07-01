import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA3499 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int nTestCase;
        int nLen;
        int nTmp;
        String[] deck;

        nTestCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= nTestCase; tc++) {
            nTmp = 0;
            sb = new StringBuilder();

            nLen = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            deck = new String[nLen];
            for (int idx = 0; idx < (nLen + 1) / 2; idx++) { // 입력된 덱의 절반까지는 2n 자리에 저장
                deck[2 * idx] = st.nextToken();
            }
            while (st.hasMoreTokens()) { // 나머지 절반은 앞에서부터 빈칸을 채움
                if (deck[2 * nTmp + 1] == null) {
                    deck[2 * nTmp + 1] = st.nextToken();
                    nTmp++;
                }
            }

            sb.append(String.format("#%d ", tc));
            for (int i = 0; i < nLen; i++) {
                sb.append(String.format("%s ", deck[i]));
            }

            System.out.println(sb);
        }
    }
}
