import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int target;
    static int count;
    static boolean[] broken;

    public static void main(String[] args) throws Exception {
        // 정보
        target = Integer.parseInt(br.readLine().trim());

        count = Integer.parseInt(br.readLine().trim());
        broken = new boolean[10];
        if (count > 0) {
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < count; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        // 풀이
        int ch;
        int order = 0;
        boolean pos;

        res = Math.abs(target - 100);

        for (int i = 0; i < 999999; i++) {
            ch = i;
            pos = true;
            order = 0;

            if (ch == 0) {
                if (broken[0]) {
                    pos = false;
                } else {
                    order = 1;
                }
            }

            // 이동 가능한 채널인지 탐색
            while (ch > 0) {
                if (broken[ch % 10]) {
                    pos = false;
                    break;
                }

                ch /= 10;
                order++;
            }

            if (pos) {
                res = Math.min(res, order + Math.abs(i - target));
            }
        }

        System.out.println(res);
    }
}
