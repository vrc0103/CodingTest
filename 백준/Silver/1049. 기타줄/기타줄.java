import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int need;
    static int brand;
    static int[] six, one;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        need = Integer.parseInt(st.nextToken());
        brand = Integer.parseInt(st.nextToken());

        six = new int[brand];
        one = new int[brand];

        for (int i = 0; i < brand; i++) {
            st = new StringTokenizer(br.readLine().trim());
            six[i] = Integer.parseInt(st.nextToken());
            one[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        Arrays.sort(six);
        Arrays.sort(one);

        int div = need / 6;
        int mod = need % 6;

        if (one[0] * 6 < six[0]) {
            res = one[0] * need;
        } else if (one[0] * mod > six[0]) {
            res = six[0] * (div + 1);
        } else {
            res = six[0] * div + one[0] * mod;
        }

    }
}
