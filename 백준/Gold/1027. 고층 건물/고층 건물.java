import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num;
    static int[] height;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        height = new int[num];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        for (int now = 0; now < num; now++) {
            int cnt = 0;

            if (now > 0) {
                float max = Integer.MIN_VALUE;

                for (int i = now - 1; i >= 0; i--) {
                    float slope = (float) (height[i] - height[now]) / (now - i);

                    if (slope > max) {
                        cnt++;
                        max = slope;
                    }
                }
            }

            if (now < num - 1) {
                float max = Integer.MIN_VALUE;

                for (int i = now + 1; i < num; i++) {
                    float slope = (float) (height[i] - height[now]) / (i - now);

                    if (slope > max) {
                        cnt++;
                        max = slope;
                    }
                }
            }

            res = Math.max(res, cnt);
        }
    }
}
