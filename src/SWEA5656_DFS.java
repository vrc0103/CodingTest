import java.io.*;
import java.util.*;

public class SWEA5656_DFS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int num, width, height;
    static int min;
    static int[][] bricks;
    static Queue<int[]> queue = new LinkedList<>();

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            getInfo();

            getBricks(0, bricks);

            sb.append(String.format("#%d %d\n", tc, min));
        }

        System.out.print(sb);
    }

    static void getInfo() throws IOException {
        st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        bricks = new int[height][width];

        for (int r = 0; r < height; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < width; c++) {
                bricks[r][c] = Integer.parseInt(st.nextToken());
                min++;
            }
        }
    }

    static void getBricks(int count, int[][] bricks) {
        if (count == num) {
            // 최솟값 갱신
            int res = 0;

            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    if (bricks[r][c] > 0) {
                        res++;
                    }
                }
            }
            min = Math.min(min, res);

            return;
        }

        int topRow;
        int[][] tmp;

        for (int i = 0; i < width; i++) {
            tmp = deepCopy(bricks);
            topRow = getTop(tmp, i);

            destroyBricks(tmp, topRow, i);
            fallBricks(tmp);
            getBricks(count + 1, tmp);

        }
    }

    static int[][] deepCopy(int[][] bricks) {
        int[][] tmp = new int[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                tmp[r][c] = bricks[r][c];
            }
        }

        return tmp;
    }

    static int getTop(int[][] bricks, int col) {
        for (int row = 0; row < height; row++) {
            if (bricks[row][col] > 0) {
                return row;
            }
        }

        // 해당 열의 모든 행이 0
        return height - 1;
    }

    static void destroyBricks(int[][] bricks, int row, int col) {
        int nowR, nowC, val;
        int nextR, nextC;
        int[] now;

        queue.add(new int[] { row, col, bricks[row][col] });
        bricks[row][col] = 0;

        while (!queue.isEmpty()) {
            now = queue.poll();
            nowR = now[0];
            nowC = now[1];
            val = now[2];

            for (int i = 1; i < val; i++) {
                for (int j = 0; j < 4; j++) {
                    nextR = nowR + i * dR[j];
                    nextC = nowC + i * dC[j];

                    if (nextR < 0 || nextR >= height || nextC < 0 || nextC >= width) {
                        continue;
                    }

                    if (bricks[nextR][nextC] > 1) {
                        queue.add(new int[] { nextR, nextC, bricks[nextR][nextC] });
                    }

                    bricks[nextR][nextC] = 0;
                }
            }
        }
    }

    static void fallBricks(int[][] bricks) {
        int idx;

        for (int c = 0; c < width; c++) {
            idx = height - 1;

            for (int r = height - 1; r >= 0; r--) {
                if (bricks[r][c] > 0) {
                    bricks[idx][c] = bricks[r][c];

                    if (idx != r) {
                        bricks[r][c] = 0;
                    }

                    idx--;
                }
            }
        }
    }
}
