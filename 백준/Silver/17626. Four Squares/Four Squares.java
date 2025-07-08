import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num;
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        int[] dp = new int[num + 1];

        dp[1] = 1;

        for(int i = 2; i <= num; i++) {
            int cnt = Integer.MAX_VALUE;

            // i - (j ^ 2) 중에서 가장 적은 숫자 개수 선택
            for(int j = 1; j * j <= i; j++) {
                int next = i - j * j;

                cnt = Math.min(cnt, dp[next]);
            }

            // 가장 적었던 개수 + 이번 제곱 수 1개
            dp[i] = cnt + 1;
        }

        res = dp[num];
    }
}
