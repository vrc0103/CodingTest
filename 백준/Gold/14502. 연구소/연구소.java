import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int row, col;
    static int countZero = 0;
    static int[][] map;
    static ArrayList<int[]> virus = new ArrayList<>();

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        // 정보
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] == 0) {
                    countZero++;
                } else if (map[r][c] == 2) {
                    virus.add(new int[] { r, c });
                }
            }
        }

        // 풀이
        comb(0, 0);

        System.out.println(res);
    }

    static void comb(int count, int idx) {
        // 벽 3개 모두 설치
        if (count == 3) {
            int changed = 0;
            int[] now;
            int nextR, nextC;
            boolean[][] visited = new boolean[row][col];
            Queue<int[]> queue = new LinkedList<>();

            for (int i = 0; i < virus.size(); i++) {
                queue.add(virus.get(i));
            }

            // 바이러스 확산
            while (!queue.isEmpty()) {
                now = queue.remove();

                for (int i = 0; i < 4; i++) {
                    nextR = now[0] + dR[i];
                    nextC = now[1] + dC[i];

                    if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                        continue;
                    }

                    if (map[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                        visited[nextR][nextC] = true;
                        queue.add(new int[] { nextR, nextC });
                        changed++;
                    }
                }
            }

            // 벽 3개 제외
            res = Math.max(res, countZero - changed - 3);

            return;
        }

        for (int r = idx / row; r < row; r++) {
            if (r == idx / row) {
                for (int c = idx % row; c < col; c++) {
                    if (map[r][c] == 0) {
                        map[r][c] = 1;
                        comb(count + 1, r * c + 1);
                        map[r][c] = 0;
                    }
                }
            } else {
                for (int c = 0; c < col; c++) {
                    if (map[r][c] == 0) {
                        map[r][c] = 1;
                        comb(count + 1, r * c + 1);
                        map[r][c] = 0;
                    }
                }
            }
        }
    }
}
