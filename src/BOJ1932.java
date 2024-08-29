import java.io.*;
import java.util.*;

public class BOJ1932 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int height;
    static int res;
    static int[][] triangle;

    public static void main(String[] args) throws IOException {
        getMax();

        System.out.println(res);
    }

    static void getMax() throws IOException {
        height = Integer.parseInt(br.readLine().trim()) + 1;

        triangle = new int[height][height];

        for (int h = 1; h < height; h++) {
            st = new StringTokenizer(br.readLine().trim());

            // 윗 행의 동일한 열, 앞 열 중 큰 값을 더해가며 누적합 저장
            for (int l = 1; l < h + 1; l++) {
                triangle[h][l] = Integer.parseInt(st.nextToken())
                        + Math.max(triangle[h - 1][l], triangle[h - 1][l - 1]);
            }
        }

        res = 0;
        for (int l = 1; l < height; l++) {
            res = Math.max(res, triangle[height - 1][l]);
        }
    }
}
