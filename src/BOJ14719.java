import java.io.*;
import java.util.*;

public class BOJ14719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int row, col;
    static int max = 0;
    static int[] height;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        height = new int[col];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < col; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, height[i]);
        }
    }

    static void getRes() {
        int start;

        for (int r = 1; r <= max; r++) {
            for (int c = 1; c < col - 1; c++) {
                // 왼쪽에 r 이상 높이의 벽이 있으면 오른쪽에 벽이 나타날 때까지 이동
                if (height[c] < r && height[c - 1] >= r) {
                    start = c;

                    while (c < col && height[c] < r) {
                        c++;
                    }

                    // 벽 있으면 해당 열까지 길이 합산
                    if (c < col) {
                        res += c - start;
                    }
                }
            }
        }
    }
}
