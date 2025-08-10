import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int exam, group;
    static int sum;
    static int[] solved;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        exam = Integer.parseInt(st.nextToken());
        group = Integer.parseInt(st.nextToken());

        solved = new int[exam];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < exam; i++) {
            solved[i] = Integer.parseInt(st.nextToken());
            sum += solved[i];
        }
    }

    static void getRes() {
        int left = 0;
        int right = sum / group;

        while (left <= right) {
            int mid = (left + right) / 2;
            int min = Integer.MAX_VALUE;
            int now = 0;
            int cnt = 0;

            for (int i = 0; i < exam; i++) {
                now += solved[i];

                if (now >= mid) {
                    min = Math.min(min, now);
                    now = 0;
                    cnt++;
                }
            }

            if (cnt >= group) {
                res = min;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}
