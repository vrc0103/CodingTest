import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int row, col, dest;
    static int res;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dirR = { 0, -1, 0, 1 };
    static int[] dirC = { 1, 0, -1, 0 };
    static Queue<Location> queue = new LinkedList<>();

    static class Location {
        int locR, locC;
        int wall;
        int len;

        public Location(int locR, int locC, int wall, int len) {
            this.locR = locR;
            this.locC = locC;
            this.wall = wall;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        getMap();

        res = getLen();

        System.out.println(res);
    }

    static void getMap() throws IOException {
        String tmp;

        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        visited = new boolean[row][col][dest + 1];

        for (int r = 0; r < row; r++) {
            tmp = br.readLine().trim();

            for (int c = 0; c < col; c++) {
                map[r][c] = tmp.charAt(c) - '0';
            }
        }
    }

    static int getLen() {
        int nextR, nextC;
        Location now;

        queue.offer(new Location(0, 0, 0, 1)); // 시작 지점 추가
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            now = queue.poll();

            if (now.locR == row - 1 && now.locC == col - 1) { // 도착
                return now.len;
            }

            for (int idx = 0; idx < 4; idx++) {
                nextR = now.locR + dirR[idx];
                nextC = now.locC + dirC[idx];

                if (nextR < 0 || nextR == row || nextC < 0 || nextC == col) { // 범위 밖 무시
                    continue;
                }

                if (map[nextR][nextC] == 0) { // 평지
                    if (!visited[nextR][nextC][now.wall]) { // 아직 지나가지 않은 경로
                        queue.offer(new Location(nextR, nextC, now.wall, now.len + 1));
                        visited[nextR][nextC][now.wall] = true;
                    }
                } else { // 벽
                    if (now.wall < dest && !visited[nextR][nextC][now.wall + 1]) { // 아직 지나가지 않은 경로, 벽 부수기 가능
                        queue.offer(new Location(nextR, nextC, now.wall + 1, now.len + 1));
                        visited[nextR][nextC][now.wall + 1] = true;
                    }
                }
            }
        }

        return -1; // 진행 불가
    }
}
