import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int a1, a0;
    static int c;
    static int n0;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        a1 = Integer.parseInt(st.nextToken());
        a0 = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(br.readLine().trim());
        n0 = Integer.parseInt(br.readLine().trim());

        if (a1 <= c && (a1 - c) * n0 + a0 <= 0) {
            res = 1;
        } else {
            res = 0;
        }
    }
}
