import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] num;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        num = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < len; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        for (int i = 0; i < len; i++) {
            int cnt = 0;

            if (i < len - 2 && num[i] > 0 && num[i + 1] > 0 && num[i + 2] > 0) {
                // 중간 개수가 더 크면 미리 2개짜리로 처리
                if (num[i + 1] > num[i + 2]) {
                    cnt = Math.min(num[i], num[i + 1] - num[i + 2]);
                    res += cnt * 5;
                    num[i] -= cnt;
                    num[i + 1] -= cnt;
                }

                cnt = Math.min(num[i], Math.min(num[i + 1], num[i + 2]));

                num[i] -= cnt;
                num[i + 1] -= cnt;
                num[i + 2] -= cnt;

                res += cnt * 7;
            }

            if (i < len - 1 && num[i] > 0 && num[i + 1] > 0) {
                cnt = Math.min(num[i], num[i + 1]);

                num[i] -= cnt;
                num[i + 1] -= cnt;

                res += cnt * 5;
            }

            if (num[i] > 0) {
                res += num[i] * 3;
                num[i] = 0;
            }
        }
    }
}
