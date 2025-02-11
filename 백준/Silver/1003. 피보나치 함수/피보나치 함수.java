import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int[][] count;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            getCount();
        }

        System.out.print(sb.toString());
    }

    static void getCount() throws IOException {
        int input = Integer.parseInt(br.readLine().trim());

        count = new int[41][2];

        count[0][0] = 1;
        count[1][1] = 1;

        for (int i = 2; i <= input; i++) {
            count[i][0] = count[i - 1][0] + count[i - 2][0];
            count[i][1] = count[i - 1][1] + count[i - 2][1];
        }

        sb.append(String.format("%d %d\n", count[input][0], count[input][1]));
    }
}
