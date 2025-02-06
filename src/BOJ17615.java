import java.io.*;

public class BOJ17615 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res = 0;
    static int len;
    static char[] input;

    public static void main(String[] args) throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        input = br.readLine().trim().toCharArray();

        int res1 = 0;
        int res2 = 0;
        int res3 = 0;
        int res4 = 0;

        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;

        for (int i = 0; i < input.length; i++) {
            if (flag1 && input[i] == 'R') {
                res1++;
            }
            if (flag2 && input[i] == 'B') {
                res2++;
            }

            // 파랑 첫 등장 시 다음 빨강부터 이동
            if (!flag1 && input[i] == 'B') {
                flag1 = true;
            }
            // 마찬가지
            if (!flag2 && input[i] == 'R') {
                flag2 = true;
            }
        }

        int last = input.length - 1;

        for (int i = last; i >= 0; i--) {
            if (flag3 && input[i] == 'R') {
                res3++;
            }
            if (flag4 && input[i] == 'B') {
                res4++;
            }

            // 파랑 첫 등장 시 다음 빨강부터 이동
            if (!flag3 && input[i] == 'B') {
                flag3 = true;
            }
            // 마찬가지
            if (!flag4 && input[i] == 'R') {
                flag4 = true;
            }
        }

        res = Math.min(Math.min(res1, res2), Math.min(res3, res4));

        System.out.println(res);
    }
}
