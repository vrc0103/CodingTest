import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int days;
    static int len;
    static int[] visitor;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        int max;
        int count;
        boolean zero = true;

        st = new StringTokenizer(br.readLine().trim());
        days = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());

        visitor = new int[days + 1];
        sum = new int[days + 1];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= days; i++) {
            visitor[i] = Integer.parseInt(st.nextToken());

            // 계산 일수 채우기 전까지는 일단 다 더함
            if (i <= len) {
                sum[i] = sum[i - 1] + visitor[i];
            }
            // 계산 일수동안만 계산
            else {
                sum[i] = sum[i - 1] - visitor[i - len] + visitor[i];
            }

            // 방문자 0인지 확인
            if (visitor[i] > 0) {
                zero = false;
            }
        }

        if (zero) {
            sb.append("SAD\n");
            return;
        }

        Arrays.sort(sum);

        max = sum[sum.length - 1];
        count = 1;

        for (int i = sum.length - 2; i >= 0; i--) {
            if (sum[i] == max) {
                count++;
            } else {
                break;
            }
        }

        sb.append(max).append("\n").append(count);
    }
}