import java.io.*;
import java.util.*;

public class BOJ14500 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int row, col;
    static int max;
    static int[] dirR = { 0, 1, 0, -1 };
    static int[] dirC = { 1, 0, -1, 0 };
    static int[][] arr;
    static boolean[][] selected;

    public static void main(String[] args) throws IOException {
        getArr();

        getMax();

        System.out.println(max);
    }

    static void getArr() throws IOException {
        st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        arr = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < col; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getMax() {
        max = 0;
        selected = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                selected[r][c] = true;
                DFS(r, c, 1, arr[r][c]);
                selected[r][c] = false;
            }
        }
    }

    static void DFS(int r, int c, int len, int val) {
        if (len == 4) {
            max = Math.max(max, val);

            return;
        }

        int nextR, nextC;

        // DFS의 경우 len = 3이고, ㅡ 모양일 때 -> ㅜ 모양 처리
        if (len == 3) {
            for (int i = 0; i < 4; i++) {
                int tmpR, tmpC;
                // 2칸 떨어져 있는데 true = ㅡ 모양
                nextR = r + 2 * dirR[i];
                nextC = c + 2 * dirC[i];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if (selected[nextR][nextC]) {
                    for (int j = 0; j < 2; j++) {
                        // ㅗ, ㅜ 모양 계산
                        tmpR = (r + nextR) / 2 + dirR[(i + 2 * j + 1) % 4];
                        tmpC = (c + nextC) / 2 + dirC[(i + 2 * j + 1) % 4];

                        if (tmpR < 0 || tmpR >= row || tmpC < 0 || tmpC >= col) {
                            continue;
                        }

                        max = Math.max(max, val + arr[tmpR][tmpC]);
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            nextR = r + dirR[i];
            nextC = c + dirC[i];

            if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                continue;
            }

            if (!selected[nextR][nextC]) {
                selected[nextR][nextC] = true;
                DFS(nextR, nextC, len + 1, val + arr[nextR][nextC]);
                selected[nextR][nextC] = false;
            }
        }
    }
}
