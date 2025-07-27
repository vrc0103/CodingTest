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

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        while (true) {
            st = new StringTokenizer(br.readLine().trim());

            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            if (day == 0) {
                break;
            }

            int res = 0;
            int[] odd = { 1, 3, 5, 7, 8, 10, 12 };

            for (int i = 1; i < month; i++) {
                if (Arrays.binarySearch(odd, i) >= 0) {
                    res += 31;
                } else if (i == 2) {
                    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                        res += 29;
                    } else {
                        res += 28;
                    }
                } else {
                    res += 30;
                }
            }

            res += day;

            sb.append(res).append("\n");
        }
    }
}
