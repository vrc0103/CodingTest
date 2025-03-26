import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        nums = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int[] sum = new int[len];

        for(int i = 0; i < len; i++) {
            sum[i] = nums[i];

            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    sum[i] = Math.max(sum[i], sum[j] + nums[i]);
                }
            }

            res = Math.max(res, sum[i]);
        }
    }
}
