import java.io.*;
import java.util.*;

public class SWEA10726_BitMasking {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int num;
    static int order;

    public static void main(String[] args) throws Exception {
        int testCase = Integer.parseInt(br.readLine().trim());
        String res;

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            order = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());

            if ((num & ((1 << order) - 1)) == ((1 << order) - 1)) {
                res = "ON";
            } else {
                res = "OFF";
            }

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }
}
