import java.io.*;

public class BOJ1463 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res;
    static int input;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        getMin();

        System.out.println(res);
    }

    static void getMin() throws IOException {
        input = Integer.parseInt(br.readLine().trim());

        if (input == 1) {
            res = 0;
            return;
        }

        cnt = new int[input + 1];

        dp_loop();
    }

    static void dp_loop() {
        cnt[1] = 0;

        for (int i = 2; i <= input; i++) {
            cnt[i] = cnt[i - 1] + 1;

            if (i % 2 == 0) {
                cnt[i] = Math.min(cnt[i], cnt[i / 2] + 1);
            }

            if (i % 3 == 0) {
                cnt[i] = Math.min(cnt[i], cnt[i / 3] + 1);
            }
        }

        res = cnt[input];
    }

    static int dp(int num) {
        // 이미 계산된 숫자 -> 바로 반환
        // 1은 횟수가 0 -> 바로 반환
        if (cnt[num] > 0 || num == 1) {
            return cnt[num];
        }

        // 계산이 완료되지 않은 숫자
        if (num % 3 == 0 && num % 2 == 0) {
            cnt[num] = Math.min(dp(num - 1), Math.min(dp(num / 3), dp(num / 2))) + 1;
        } else if (num % 3 == 0) {
            cnt[num] = Math.min(dp(num / 3), dp(num - 1)) + 1;
        } else if (num % 2 == 0) {
            cnt[num] = Math.min(dp(num / 2), dp(num - 1)) + 1;
        } else {
            cnt[num] = dp(num - 1) + 1;
        }

        return cnt[num];
    }
}
