import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.println(sb);
    }

    static void getRes() throws Exception {
        char[] input = br.readLine().trim().toCharArray();
        int[] arr = new int[input.length];
        int sum = 0;

        for (int i = 0; i < input.length; i++) {
            arr[i] = input[i] - '0';
            sum += arr[i];
        }

        Arrays.sort(arr);

        // 30의 배수 = 10의 배수 && 3의 배수
        if (arr[0] == 0 && sum % 3 == 0) {
            for (int i = arr.length - 1; i >= 0; i--) {
                sb.append(arr[i]);
            }
        } else {
            sb.append(-1);
        }
    }
}
