import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;

    static int res;
    static int size;
    static int[][] map;
    static int[][] cnt;

    static int[] dR = { 0, -1, 0, 1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        size = Integer.parseInt(br.readLine().trim());

        map = new int[size][size];

        for (int r = 0; r < size; r++) {
            String str = br.readLine().trim();

            for (int c = 0; c < size; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }
    }

    static void getRes() {
        cnt = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(cnt[i], size * size);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        pq.offer(new int[] { 0, 0, 0 });

        while (true) {
            int[] now = pq.poll();

            if (now[0] == size - 1 && now[1] == size - 1) {
                res = now[2];

                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];

                if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                    continue;
                }

                int cost = now[2];

                if (map[nextR][nextC] == 0) {
                    cost++;
                }

                if (cnt[nextR][nextC] > cost) {
                    cnt[nextR][nextC] = cost;

                    pq.offer(new int[] { nextR, nextC, cost });
                }
            }
        }
    }
}
