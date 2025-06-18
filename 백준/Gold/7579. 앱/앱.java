import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num, need;
    static int sum;
    static int[][] info;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        need = Integer.parseInt(st.nextToken());

        info = new int[num + 1][2];

        StringTokenizer st1 = new StringTokenizer(br.readLine().trim());
        StringTokenizer st2 = new StringTokenizer(br.readLine().trim());

        for (int i = 1; i <= num; i++) {
            info[i][0] = Integer.parseInt(st1.nextToken());
            info[i][1] = Integer.parseInt(st2.nextToken());

            sum += info[i][1];
        }
    }

    static void getRes() {
        // 비용 합 내에서 메모리가 목표 이상인 비용 찾기
        // -> 배낭 용량 = 비용 합 , 메모리 = 가치
        int[][] dp = new int[num + 1][sum + 1];

        for (int i = 1; i <= num; i++) {
            for (int j = 0; j <= sum; j++) {
                // i번 앱 종료 X
                dp[i][j] = dp[i - 1][j];

                // i번 앱을 활성화시킬 비용이 남은 경우
                if (j >= info[i][1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - info[i][1]] + info[i][0]);
                }
            }
        }

        for (int j = 0; j <= sum; j++) {
            if (dp[num][j] >= need) {
                res = j;
                return;
            }
        }
    }
}
