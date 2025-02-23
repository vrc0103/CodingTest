import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] input, dp;

    public static void main(String[] args) throws IOException {
        getMax();

        System.out.println(res);
    }

    static void getMax() throws IOException {
        len = Integer.parseInt(br.readLine().trim());
        input = new int[len];
        dp = new int[len];

        // 누적 합 배열 생성 및 최댓값, 최솟값 갱신
        st = new StringTokenizer(br.readLine().trim());
        for (int l = 0; l < len; l++) {
            input[l] = Integer.parseInt(st.nextToken());

            if (l == 0) {
                dp[l] = input[l];
            } else {
                dp[l] = Math.max(dp[l - 1] + input[l], input[l]);
            }
        }

        Arrays.sort(dp);
        res = dp[len - 1];
    }
}
