import java.io.*;
import java.util.*;

public class BOJ11501 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int day;
    static int[] stock;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            getGain();
        }

        System.out.print(sb.toString());
    }

    static void getGain() throws IOException {
        int price = 0;
        long sum = 0;

        day = Integer.parseInt(br.readLine().trim());
        stock = new int[day];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < day; i++) {
            stock[i] = Integer.parseInt(st.nextToken());
        }

        // 판매
        for (int i = day - 1; i >= 0; i--) {
            if (stock[i] > price) {
                price = stock[i];
            }

            sum += price - stock[i];
        }

        sb.append(sum).append("\n");
    }
}