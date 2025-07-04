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
        int order = Integer.parseInt(br.readLine().trim());
        int[][] dp = new int[order][10];

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < order; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= 10007;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            res += dp[order - 1][i];
            res %= 10007;
        }
    }
}
