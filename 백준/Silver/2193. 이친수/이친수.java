import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static long res;
    static long[] fibonacci;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        int num = Integer.parseInt(br.readLine().trim());
        fibonacci = new long[num];

        res = fib(num - 1);
    }

    static long fib(int num) {
        if (fibonacci[num] > 0) {
            return fibonacci[num];
        }

        if (num == 1 || num == 0) {
            return fibonacci[num] = 1;
        }

        return fibonacci[num] = fib(num - 1) + fib(num - 2);
    }
}
