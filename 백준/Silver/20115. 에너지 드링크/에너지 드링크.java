import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static double res = 0.0;
    static int num;
    static int[] drinks;

    public static void main(String[] args) throws Exception {
        // 정보
        num = Integer.parseInt(br.readLine().trim());
        drinks = new int[num];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            drinks[i] = Integer.parseInt(st.nextToken());
        }

        // 풀이
        Arrays.sort(drinks);

        res = drinks[num - 1];

        for (int i = 0; i < num - 1; i++) {
            res += (double) drinks[i] / 2;
        }

        System.out.println(res);
    }
}