import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[] dR = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] dC = { 1, 2, 2, 1, -1, -2, -2, -1 };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            getRes();
        }

        System.out.println(sb);
    }

    static void getRes() throws Exception {
        int size = Integer.parseInt(br.readLine().trim());
        boolean[][] visited = new boolean[size][size];

        int[] start = new int[2];
        st = new StringTokenizer(br.readLine().trim());
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());

        int[] dest = new int[2];
        st = new StringTokenizer(br.readLine().trim());
        dest[0] = Integer.parseInt(st.nextToken());
        dest[1] = Integer.parseInt(st.nextToken());

        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] { start[0], start[1], 0 });
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[0] == dest[0] && now[1] == dest[1]) {
                sb.append(now[2]).append("\n");
                return;
            }

            for (int i = 0; i < 8; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];

                if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                    continue;
                }

                if (!visited[nextR][nextC]) {
                    q.offer(new int[] { nextR, nextC, now[2] + 1 });
                    visited[nextR][nextC] = true;
                }
            }
        }
    }
}
