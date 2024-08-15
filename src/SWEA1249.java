import java.io.*;
import java.util.*;

public class SWEA1249 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int size;
    static int res;
    static int[][] map;
    static boolean[][] selected;

    static int[] dirR = { 0, 1, 0, -1 };
    static int[] dirC = { 1, 0, -1, 0 };

    static class Route implements Comparable<Route> {
        int row, col;
        int val;

        public Route(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public int compareTo(Route o) {
            return this.val - o.val;
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            getMap();

            getMinRoute();

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb.toString());
    }

    static void getMap() throws IOException {
        String input;

        size = Integer.parseInt(br.readLine());

        map = new int[size][size];

        for (int i = 0; i < size; i++) {
            input = br.readLine();

            for (int j = 0; j < size; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
    }

    static void getMinRoute() {
        int nowR, nowC, nowV;
        int nextR, nextC;
        Route rt;
        PriorityQueue<Route> pq = new PriorityQueue<>();

        pq.add(new Route(0, 0, 0));

        selected = new boolean[size][size];
        selected[0][0] = true;

        while (true) {
            rt = pq.poll();

            nowR = rt.row;
            nowC = rt.col;
            nowV = rt.val;

            // 도착
            if (nowR == size - 1 && nowC == size - 1) {
                res = nowV;
                return;
            }

            for (int i = 0; i < 4; i++) {
                nextR = nowR + dirR[i];
                nextC = nowC + dirC[i];

                if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                    continue;
                }

                if (!selected[nextR][nextC]) {
                    selected[nextR][nextC] = true;
                    pq.add(new Route(nextR, nextC, nowV + map[nextR][nextC]));
                }
            }
        }
    }
}
