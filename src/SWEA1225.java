import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA1225 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static void EncryptArr(int[] nInput) {
        int nTmp;

        for (int i = 1; i <= 5; i++) {
            nTmp = Math.max(nInput[0] - i, 0); // 음수이면 0으로

            for (int j = 0; j < 7; j++) { // 한 칸씩 앞으로
                nInput[j] = nInput[j + 1];
            }
            nInput[7] = nTmp;

            if (nInput[7] == 0) {
                return;
            }
        }

        EncryptArr(nInput); // 마지막 숫자가 0이 될때까지 재귀
    }

    public static void main(String[] args) throws IOException {
        int nTestCase;
        int[] nInput = new int[8];

        for (int tc = 1; tc <= 10; tc++) {
            sb = new StringBuilder();
            nTestCase = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());

            for (int idx = 0; idx < 8; idx++) { // 입력받은 배열로 초기화
                nInput[idx] = Integer.parseInt(st.nextToken());
            }

            EncryptArr(nInput);

            sb.append(String.format("#%d", nTestCase));
            for (int idx = 0; idx < 8; idx++) {
                sb.append(String.format(" %d", nInput[idx]));
            }
            System.out.println(sb);
        }
    }
}
