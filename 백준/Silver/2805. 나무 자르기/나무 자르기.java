import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num, target;
    static int max;
    static int[] height;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        height = new int[num];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < num; i++) {
            height[i] = Integer.parseInt(st.nextToken());

            max = Math.max(max, height[i]);
        }
    }

    static void getRes() {
        int left = 0;
        int right = max;

        while(left <= right) {
            int mid = (left + right) / 2;
            long sum = 0;

            for(int i = 0; i < num; i++) {
                sum += Math.max(0, height[i] - mid);
            }

            if(sum >= target) {
                res = Math.max(res, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}
