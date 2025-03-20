import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;

    static int res;
    static int num;
    static int cost[];
    static int gain[];

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        cost = new int[num + 1];
        gain = new int[num + 1];

        StringTokenizer st1 = new StringTokenizer(br.readLine().trim());
        StringTokenizer st2 = new StringTokenizer(br.readLine().trim());
        for(int i = 1; i <= num; i++) {
            cost[i] = Integer.parseInt(st1.nextToken());
            gain[i] = Integer.parseInt(st2.nextToken());
        }
    }

    static void getRes() {
        int[][] dp = new int[num + 1][100];

        for(int i = 1; i <= num; i++) {
            for(int j = 0; j < 100; j++) {
                dp[i][j] = dp[i - 1][j];    // 이전과 동일

                if(cost[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], gain[i] + dp[i - 1][j - cost[i]]);
                }
            }
        }

        res = dp[num][99];
    }
}
