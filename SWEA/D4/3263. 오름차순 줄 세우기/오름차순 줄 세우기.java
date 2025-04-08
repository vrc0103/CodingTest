import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int len;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = 0;
        len = Integer.parseInt(br.readLine().trim());
        arr = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int[] LIS = new int[len + 1];

        for(int i = 0; i < len; i++) {
            LIS[arr[i]] = LIS[arr[i] - 1] + 1;
            res = Math.max(res, LIS[arr[i]]);
        }

        res = len - res;
    }
}
