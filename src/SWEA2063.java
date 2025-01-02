import java.io.*;
import java.util.*;

public class SWEA2063 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        len = Integer.parseInt(br.readLine().trim());
        nums = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        res = nums[len / 2];
    }
}
