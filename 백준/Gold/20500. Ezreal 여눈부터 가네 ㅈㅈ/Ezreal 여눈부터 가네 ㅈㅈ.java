import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int N;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.print(res);
    }

    static void getRes() throws Exception {
        N = Integer.parseInt(br.readLine().trim());

        if (N == 1) {
            res = 0;
            return;
        }

        if (N == 2) {
            res = 1;
            return;
        }

        int[][] dp = new int[N + 1][3];

        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[2][2] = 0;

        for(int i = 3; i <= N; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % 1_000_000_007;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 1_000_000_007;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 1_000_000_007;
        }

        res = dp[N][0];
    }
}

