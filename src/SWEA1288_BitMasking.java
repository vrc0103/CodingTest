import java.io.*;
import java.util.*;

public class SWEA1288_BitMasking {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int res;
    static int num;

    public static void main(String[] args) throws Exception {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            num = Integer.parseInt(br.readLine().trim());

            countSheep();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void countSheep() {
        int idx = 0;
        int counted = 0;
        int tmp;

        // 비트 마스킹
        while ((counted & ((1 << 10) - 1)) != ((1 << 10) - 1)) {
            tmp = num * ++idx;

            while (tmp > 0) {
                counted = counted | (1 << (tmp % 10));
                tmp /= 10;
            }
        }

        res = num * idx;
    }
}
