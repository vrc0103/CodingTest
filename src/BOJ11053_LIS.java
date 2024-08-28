import java.io.*;
import java.util.*;

public class BOJ11053_LIS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] input;
    static int[] LIS;

    public static void main(String[] args) throws IOException {
        getLIS();

        System.out.println(res);
    }

    static void getLIS() throws IOException {
        len = Integer.parseInt(br.readLine().trim()) + 1;

        input = new int[len];
        LIS = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i < len; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        res = 1;
        for (int i = 1; i < len; i++) {
            LIS[i] = 1;

            // 이번 원소보다 값이 작은 원소 중에서 부분 수열의 길이가 가장 긴 값 +1로 갱신
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i] && LIS[j] >= LIS[i]) {
                    LIS[i] = LIS[j] + 1;
                }
            }

            res = Math.max(res, LIS[i]);
        }
    }
}
