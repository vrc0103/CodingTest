import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int len;
    static int cnt;
    static int[] nums;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        // 정보
        len = Integer.parseInt(br.readLine().trim());

        nums = new int[len + 1];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 1; i <= len; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 풀이
        dp = new boolean[len + 1][len + 1];

        /*
         * dp 계산
         * 배열이 채워지는 순서 때문에 바깥쪽 반복문을 j, 안쪽 반복문을 i로 설정
         * i, j 순서이면 dp[1][5] 계산 시 dp[2][4] 계산이 안되어 있음
         */
        for(int j = 1; j <= len; j++) {
            for(int i = 1; i <= j; i++) {
                // 한 글자 : 무조건 맞음
                if(i == j) {
                    dp[i][j] = true;
                }
                // 두 글자 : 같은 글자면 맞음
                else if(j == i + 1 && nums[i] == nums[j]) {
                    dp[i][j] = true;
                }
                // 일반적인 경우 : 양 끝 글자가 동일하고 가운데가 팰린드롬을 이루면 맞음
                else if(nums[i] == nums[j] && dp[i + 1][j - 1]){
                    dp[i][j] = true;
                }
            }
        }

        // 출력
        int left, right;

        cnt = Integer.parseInt(br.readLine().trim());
        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            left = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());

            sb.append(dp[left][right] ? 1 : 0).append("\n");
        }

        System.out.print(sb);
    }
}
