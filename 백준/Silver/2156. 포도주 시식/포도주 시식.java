import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res;
    static int len;
    static int[] input;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        getMax();

        System.out.println(res);
    }

    static void getMax() throws IOException {
        len = Integer.parseInt(br.readLine().trim());

        input = new int[len + 1];
        dp = new int[len + 1];

        for (int i = 1; i < len + 1; i++) {
            input[i] = Integer.parseInt(br.readLine().trim());
        }

        dp[0] = 0;
        dp[1] = input[1];
        if (len > 1) {
            dp[2] = input[1] + input[2];
        }

        // https://st-lab.tistory.com/135
        int now;

        for (int idx = 3; idx < len + 1; idx++) {
            // 현재 와인을 마시는 경우
            // 1. (현재 - 1)번째 와인을 마시지 않고 (현재 - 2)까지의 음주량과 합산
            // 2. (현재 - 1)번째 와인을 마시고 (현재 - 3)까지의 음주량과 합산
            now = Math.max(dp[idx - 2], dp[idx - 3] + input[idx - 1]) + input[idx];

            // 현재 와인을 마시는 경우와 마시지 않는 경우를 비교하여 최댓값을 저장
            dp[idx] = Math.max(now, dp[idx - 1]);
        }

        res = dp[len];
    }
}
