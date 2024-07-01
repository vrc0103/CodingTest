import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SWEA1289 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int nTestCase;
    static String sInput;

    public static int countToggle() {
        int Res = 0;
        int Len = sInput.length();

        if (sInput.charAt(0) == '1') {
            Res++;
        }
        for (int i = 0; i < Len - 1; i++) {
            if (sInput.charAt(i) != sInput.charAt(i + 1)) {
                Res++;
            }
        }

        return Res;
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            sInput = br.readLine().trim();
            sb.append(String.format("#%d %d\n", tc, countToggle()));
        }

        System.out.print(sb);
    }
}
