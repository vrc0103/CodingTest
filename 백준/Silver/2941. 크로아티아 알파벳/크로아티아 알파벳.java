import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res;
    static String input;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int len;
        char c;

        input = br.readLine().trim();
        len = input.length();

        res = len;

        for (int i = 1; i < len; i++) {
            c = input.charAt(i);

            // 2글자 이상인 크로아티아 알파벳 판별
            if (c == '=') {
                res--;

                // 'dz='인 경우 추가로 1 차감
                if (i >= 2 && input.charAt(i - 2) == 'd' && input.charAt(i - 1) == 'z') {
                    res--;
                }
            } else if (c == '-') {
                res--;
            } else if (c == 'j' && (input.charAt(i - 1) == 'l' || input.charAt(i - 1) == 'n')) {
                res--;
            }
        }
    }
}
