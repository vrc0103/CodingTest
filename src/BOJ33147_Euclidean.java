import java.io.*;
import java.util.*;

public class BOJ33147_Euclidean {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String res;
    static int len;
    static int gap;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        // 정보
        st = new StringTokenizer(br.readLine().trim());
        len = Integer.parseInt(st.nextToken());
        gap = Integer.parseInt(st.nextToken());

        nums = new int[len];
        res = "YES";

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 풀이
        int A = len;
        int B = gap;
        int tmp;

        // 유클리드 호제법
        while (B > 0) {
            tmp = A % B;
            A = B;
            B = tmp;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] % A != i % A) {
                res = "NO";
                break;
            }
        }

        System.out.println(res);
    }
}
