import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int num, vol;
    static int[][] info;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        vol = Integer.parseInt(st.nextToken());

        info = new int[num + 1][2];
        dp = new int[vol + 1];

        for(int i = 1; i <= num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int size = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            info[i][0] = cost;
            info[i][1] = size;
        }
    }

    static void getRes() {
        for(int i = 1; i <= num; i++) {
            for (int j = vol; j >= info[i][1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - info[i][1]] + info[i][0]);
            }
        }

        res = dp[vol];
    }
}
