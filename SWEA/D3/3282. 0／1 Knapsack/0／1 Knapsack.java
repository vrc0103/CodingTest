import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int num, vol;
    static int[][] info;
    static int[][] dp;

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
        dp = new int[num + 1][vol + 1];

        for(int i = 1; i <= num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int size = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            info[i][0] = cost;
            info[i][1] = size;
        }
    }

    static void getRes() {
        for(int i = 1; i <= vol; i++) {
            for(int j = 1; j <= num; j++) {
                if(i < info[j][1]) {
                    dp[j][i] = dp[j - 1][i];
                } else {
                    dp[j][i] = Math.max(dp[j - 1][i], dp[j - 1][i - info[j][1]] + info[j][0]);
                }
            }
        }

        res = dp[num][vol];
    }
}
