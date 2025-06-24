import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static long res;
    static long num1, num2;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num1 = Long.parseLong(st.nextToken());
        num2 = Long.parseLong(st.nextToken());

        long big = Math.max(num1, num2);
        long small = Math.min(num1, num2);

        while (small > 0) {
            long tmp = small;

            small = big % small;
            big = tmp;
        }

        res = num1 * num2 / big;
    }
}
