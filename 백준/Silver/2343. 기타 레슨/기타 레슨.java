import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num, max;
    static int[] lens;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        lens = new int[num];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < num; i++) {
            lens[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int left = 0, right = 0;

        for(int i = 0; i < num; i++) {
            left = Math.max(left, lens[i]);
            right += lens[i];
        }

        while(left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            int cnt = 1;

            for(int i = 0; i < num; i++) {
                if(sum + lens[i] <= mid) {
                    sum += lens[i];
                } else {
                    cnt++;
                    sum = lens[i];
                }
            }

            if(cnt <= max) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
}
