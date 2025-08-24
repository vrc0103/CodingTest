import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        int num = Integer.parseInt(br.readLine().trim());
        int len = Integer.toString(num).length();

        for (int i = 1; i < len; i++) {
            int cnt = 9 * (int) Math.pow(10, i - 1);
            num -= cnt;
            res += cnt * i;
        }

        res += num * len;
    }
}
