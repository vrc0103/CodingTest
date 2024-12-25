import java.io.*;
import java.util.*;

public class BOJ9465 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int length;
    static int[][] point;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            setInfo();

            getRes();
        }

        System.out.print(sb.toString());
    }

    static void setInfo() throws IOException {
        length = Integer.parseInt(br.readLine().trim());

        point = new int[2][length + 1];

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int j = 1; j <= length; j++) {
                point[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        /*
         * DP
         * 0행 n열 기준
         * 1) (1, n-1), (0, n) 선택
         * 2) (1, n-2), (0, n) 선택
         */

        int[][] dp = new int[2][length + 1];

        dp[0][1] = point[0][1];
        dp[1][1] = point[1][1];

        for (int i = 2; i <= length; i++) {
            dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + point[0][i];
            dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + point[1][i];
        }

        sb.append(Math.max(dp[0][length], dp[1][length])).append("\n");
    }
}
