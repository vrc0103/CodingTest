import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int row, col, limit;
    static int[][] map;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

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
        limit = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int[] gramr = null;
        int[][] cnt = new int[row][col];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        res = limit + 1;

        for (int i = 0; i < row; i++) {
            Arrays.fill(cnt[i], row * col);
        }

        pq.offer(new int[] { 0, 0, 0 });
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (now[0] == row - 1 && now[1] == col - 1) {
                res = now[2];
                break;
            }

            if (map[now[0]][now[1]] == 2) {
                gramr = now;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];
                int cost = now[2] + 1;

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if (map[nextR][nextC] != 1 && cnt[nextR][nextC] > cost) {
                    cnt[nextR][nextC] = cost;
                    pq.offer(new int[] { nextR, nextC, cost });
                }
            }
        }

        if (gramr != null) {
            int time = gramr[2] + (row - 1 - gramr[0]) + (col - 1 - gramr[1]);
            res = Math.min(res, time);
        }

        sb.append(res <= limit ? res : "Fail");
    }
}
