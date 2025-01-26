import java.io.*;
import java.util.*;

public class BOJ33147 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String res;
    static int len;
    static int gap;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        // 정보
        boolean asc = true;

        st = new StringTokenizer(br.readLine().trim());
        len = Integer.parseInt(st.nextToken());
        gap = Integer.parseInt(st.nextToken());

        nums = new int[len];
        res = "YES";

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(st.nextToken());

            // 오름차순 정렬된 상태인지 확인
            if (i > 0 && nums[i] < nums[i - 1]) {
                asc = false;
            }
        }

        // 풀이
        // 배열 길이와 간격이 동일한 경우 정렬된 상태가 아니라면 절대 불가능
        if (len == gap) {
            if (!asc) {
                res = "NO";
            }
        } else {
            // 최대 공약수 찾기
            int factor = 1;

            for (int i = gap; i >= 1; i--) {
                if (len % i == 0 && gap % i == 0) {
                    factor = i;
                    break;
                }
            }

            // 간격 확인
            for (int i = 0; i < len; i++) {
                if (Math.abs(nums[i] - nums[(i + factor) % len]) % factor != 0) {
                    res = "NO";
                    break;
                }
            }
        }

        System.out.println(res);
    }
}
