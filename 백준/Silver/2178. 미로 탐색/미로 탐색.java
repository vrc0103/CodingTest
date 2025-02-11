import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int row, col;
    static int res;
    static int[][] map;
    static boolean[][] selected;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    static class Route {
        int row, col;
        int len;

        public Route(int row, int col, int len) {
            this.row = row;
            this.col = col;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        getMap();

        findRoute();

        System.out.println(res);
    }

    static void getMap() throws IOException {
        String input;

        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        selected = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            input = br.readLine().trim();

            for (int c = 0; c < col; c++) {
                map[r][c] = input.charAt(c) - '0';
            }
        }
    }

    static void findRoute() {
        int nextR, nextC;
        Route now;
        Queue<Route> queue = new LinkedList<>();

        queue.add(new Route(0, 0, 1));
        selected[0][0] = true;

        while (!queue.isEmpty()) {
            now = queue.poll();

            if (now.row == row - 1 && now.col == col - 1) {
                res = now.len;
                return;
            }

            for (int i = 0; i < 4; i++) {
                nextR = now.row + dR[i];
                nextC = now.col + dC[i];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if (map[nextR][nextC] == 1 && !selected[nextR][nextC]) {
                    queue.add(new Route(nextR, nextC, now.len + 1));
                    selected[nextR][nextC] = true;
                }
            }
        }
    }
}
