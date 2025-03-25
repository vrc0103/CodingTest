import java.io.*;

public class Main {
    static BufferedReader br;

    static int res;
    static String str1, str2;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        str1 = br.readLine().trim();
        str2 = br.readLine().trim();
    }

    static void getRes() {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                // 이번 문자열이 동일하면
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // 각 문자열에서 한 칸씩 앞에 있던 값에 +1
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    res = Math.max(res, dp[i][j]);
                }
            }
        }
    }
}
