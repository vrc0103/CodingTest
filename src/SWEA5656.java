import java.io.*;
import java.util.*;

public class SWEA5656 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int num, width, height;
    static int min;
    static int[] select;
    static int[][] bricks;
    static int[][] tmp;
    static Queue<Brick> queue = new LinkedList<>();

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    static class Brick {
        int row, col;
        int val;

        public Brick(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            getInfo();

            getBricks();

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
        tmp = new int[height][width];

        for (int r = 0; r < height; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < width; c++) {
                bricks[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getBricks() {
        select = new int[num];
        min = Integer.MAX_VALUE;

        permutationRepetition(0);
    }

    static void permutationRepetition(int count) {
        if (count == num) {
            getMin();
            return;
        }

        for (int i = 0; i < width; i++) {
            select[count] = i;
            permutationRepetition(count + 1);
        }
    }

    static void getMin() {
        // 깊은 복사
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                tmp[r][c] = bricks[r][c];
            }
        }

        // 벽돌 부수기
        int idx;

        for (int col : select) {
            for (int row = 0; row < height; row++) {
                if (tmp[row][col] > 0) {
                    queue.add(new Brick(row, col, tmp[row][col]));
                    destroyBricks();
                    break;
                }
            }

            // 빈칸 메꾸기
            for (int c = 0; c < width; c++) {
                idx = height - 1;

                for (int r = height - 1; r >= 0; r--) {
                    if (tmp[r][c] > 0) {
                        tmp[idx][c] = tmp[r][c];

                        if (idx != r) {
                            tmp[r][c] = 0;
                        }

                        idx--;
                    }
                }
            }
        }

        // 남은 벽돌 계산
        int res = 0;

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (tmp[r][c] > 0) {
                    res++;
                }
            }
        }

        // System.out.println(Arrays.toString(select) + " " + res);

        min = Math.min(min, res);
    }

    static void destroyBricks() {
        int row, col, val;
        int nextR, nextC;
        Brick brick;

        while (!queue.isEmpty()) {
            brick = queue.poll();
            row = brick.row;
            col = brick.col;
            val = brick.val;

            tmp[row][col] = 0;

            for (int i = 1; i < val; i++) {
                for (int j = 0; j < 4; j++) {
                    nextR = row + i * dR[j];
                    nextC = col + i * dC[j];

                    if (nextR < 0 || nextR >= height || nextC < 0 || nextC >= width) {
                        continue;
                    }

                    if (tmp[nextR][nextC] > 1) {
                        queue.add(new Brick(nextR, nextC, tmp[nextR][nextC]));
                    }

                    tmp[nextR][nextC] = 0;
                }
            }
        }
    }
}
