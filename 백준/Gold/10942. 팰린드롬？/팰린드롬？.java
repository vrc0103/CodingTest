import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int len, count;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        int start, end;

        len = Integer.parseInt(br.readLine().trim());
        nums = new int[len + 1];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= len; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        count = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine().trim());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            if (isPalindrome(start, end)) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
    }

    static boolean isPalindrome(int start, int end) {
        int left = start;
        int right = end;

        while (left <= right) {
            if (nums[left] != nums[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
