import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int row, col;
    static int[][] sum;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        sum = new int[row + 1][col + 1];

        for (int r = 1; r <= row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 1; c <= col; c++) {
                sum[r][c] = sum[r][c - 1] + sum[r - 1][c] - sum[r - 1][c - 1] + Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() throws Exception {
        int cnt = Integer.parseInt(br.readLine().trim());

        while (cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int res = sum[x2][y2] - sum[x1][y2] - sum[x2][y1] + sum[x1][y1];
            sb.append(res).append("\n");
        }
    }
}
