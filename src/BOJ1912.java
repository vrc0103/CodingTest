import java.io.*;
import java.util.*;

public class BOJ1912 {
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
                // 현재 원소까지의 누적 합과 원소 단일 값을 비교하여 더 큰 값 선택
                dp[l] = Math.max(dp[l - 1] + input[l], input[l]);
            }
        }

        // 정렬 후 마지막 값(최댓값) 선택
        Arrays.sort(dp);
        res = dp[len - 1];
    }

    // 뭔가 빠진 조건이 있는 듯
    static void getMax_() throws IOException {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int idxMin = 0;

        len = Integer.parseInt(br.readLine().trim()) + 1;
        input = new int[len];
        dp = new int[len];

        // 누적 합 배열 생성 및 최댓값, 최솟값 갱신
        st = new StringTokenizer(br.readLine().trim());
        for (int l = 1; l < len; l++) {
            input[l] = Integer.parseInt(st.nextToken());
            dp[l] = dp[l - 1] + input[l];

            if (dp[l] < min) {
                min = dp[l];
                idxMin = l;
            }

            // 누적 합과 현재 원소를 비교하여 최댓값 갱신
            max = Math.max(max, Math.max(input[l], dp[l]));
        }

        res = max;

        // 누적 합 중 최솟값이 음수이면 이후의 누적합들을 탐색하며 최댓값 계산 및 갱신
        if (min < 0 && idxMin < len - 1) {
            for (int i = idxMin + 1; i < len; i++) {
                res = Math.max(res, dp[i] - min);
            }
        }
    }
}
