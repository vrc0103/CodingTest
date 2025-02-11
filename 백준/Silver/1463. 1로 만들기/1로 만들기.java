import java.io.*;
import java.util.*;

public class Main {
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
}
