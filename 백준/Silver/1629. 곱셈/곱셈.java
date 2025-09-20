import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int base, pow, mod;
    static Map<Integer, Integer> num;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        base = Integer.parseInt(st.nextToken());
        pow = Integer.parseInt(st.nextToken());
        mod = Integer.parseInt(st.nextToken());

        num = new HashMap<>();
    }

    static void getRes() {
        num.put(1, base % mod);
        res = conq(pow);
    }

    static int conq(int pow) {
        if (pow == 1) {
            return num.get(1);
        }

        if (num.get(pow) == null) {
            int div = pow / 2;
            long mid1 = conq(div);
            long mid2 = conq(pow - div);
            long mid = mid1 * mid2 % mod;

            num.put(pow, (int) mid);
        }

        return num.get(pow);
    }
}
