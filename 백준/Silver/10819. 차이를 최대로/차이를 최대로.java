import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] height;
    static int[] arr;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        height = new int[len];
        arr = new int[len];
        selected = new boolean[len];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        comb(0);

        System.out.println(res);
    }

    static void comb(int cnt) {
        if(cnt == len) {
            int sum = 0;

            for(int i = 1; i < len; i++) {
                sum += Math.abs(arr[i] - arr[i - 1]);
            }

            res = Math.max(res, sum);
        }

        for(int i = 0; i < len; i++) {
            if(!selected[i]) {
                selected[i] = true;
                arr[cnt] = height[i];
                comb(cnt + 1);
                selected[i] = false;
            }
        }
    }
}
