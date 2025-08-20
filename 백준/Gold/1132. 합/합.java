import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static long res;
    static int cnt;
    static int[] num;
    static char[][] input;
    static boolean[] notZero;
    static boolean[] used;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        cnt = Integer.parseInt(br.readLine().trim());

        num = new int[10];
        notZero = new boolean[10];
        used = new boolean[10];
        input = new char[cnt][12];

        for (int i = 0; i < cnt; i++) {
            char[] chs = br.readLine().trim().toCharArray();
            int diff = 12 - chs.length;
            notZero[chs[0] - 'A'] = true;

            for (int j = diff; j < 12; j++) {
                input[i][j] = chs[j - diff];
            }
        }
    }

    static void getRes() {
        match(0);
    }

    static void match(int idx) {
        if (idx == 10) {
            computeRes();

            return;
        }

        for (int i = 0; i < 10; i++) {
            if (used[i]) {
                continue;
            }

            if (i == 0 && notZero[idx]) {
                continue;
            }

            num[idx] = i;
            used[i] = true;
            match(idx + 1);
            used[i] = false;
        }
    }

    static void computeRes() {
        long computed = 0;

        for (int i = 0; i < cnt; i++) {
            long tmp = 0;

            for (int j = 0; j < 12; j++) {
                if (input[i][j] >= 'A') {
                    tmp = tmp * 10 + num[input[i][j] - 'A'];
                }
            }

            computed += tmp;
        }

        res = Math.max(res, computed);
    }
}
