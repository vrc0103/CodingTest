import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int min, city;
    static int[][] info;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        min = Integer.parseInt(st.nextToken());
        city = Integer.parseInt(st.nextToken());

        info = new int[city][2];

        for (int i = 0; i < city; i++) {
            st = new StringTokenizer(br.readLine().trim());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int[] dp = new int[min + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= min; i++) {
            for (int j = 0; j < city; j++) {
                dp[i] = Math.min(dp[i], dp[Math.max(i - info[j][1], 0)] + info[j][0]);
            }
        }

        res = dp[min];
    }
}
