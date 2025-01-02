import java.io.*;

public class SWEA2058 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res = 0;
    static String input;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        input = br.readLine().trim();

        for (int i = 0; i < input.length(); i++) {
            res += input.charAt(i) - '0';
        }
    }
}
