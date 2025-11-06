import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int[] start = new int[2];
        int[] end = new int[2];

        st = new StringTokenizer(br.readLine().trim());
        start[0] = Integer.parseInt(st.nextToken()) - 1;
        start[1] = Integer.parseInt(st.nextToken()) - 1;
        end[0] = Integer.parseInt(st.nextToken()) - 1;
        end[1] = Integer.parseInt(st.nextToken()) - 1;

        char[][] map = new char[row][];
        for (int i = 0; i < row; i++) {
            map[i] = br.readLine().trim().toCharArray();
        }

        int res = 0;

        int[] dR = { 0, 1, 0, -1 };
        int[] dC = { 1, 0, -1, 0 };
        boolean[][] visited = new boolean[row][col];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[] { start[0], start[1], 0 });
        visited[start[0]][start[1]] = true;

        while (true) {
            int[] now = pq.poll();

            if (now[0] == end[0] && now[1] == end[1]) {
                res = now[2] + 1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col || visited[nextR][nextC]) {
                    continue;
                }

                int nextD = now[2];
                if (map[nextR][nextC] == '1') {
                    nextD += 1;
                }

                pq.offer(new int[] { nextR, nextC, nextD });
                visited[nextR][nextC] = true;
            }
        }

        System.out.println(res);
    }
}
