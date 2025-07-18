import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int E, S, M;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        E = Integer.parseInt(st.nextToken()) - 1;
        S = Integer.parseInt(st.nextToken()) - 1;
        M = Integer.parseInt(st.nextToken()) - 1;
    }

    static void getRes() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (i % 15 == E && i % 28 == S && i % 19 == M) {
                res = i + 1;
                break;
            }
        }
    }
}
