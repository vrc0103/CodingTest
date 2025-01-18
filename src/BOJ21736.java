import java.io.*;
import java.util.*;

public class BOJ21736 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int res = 0;
    static int row, col;
    static char[][] map;
    static boolean[][] visited;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        String input;
        Queue<int[]> queue = new LinkedList<>();

        // 기본 정보
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        visited = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            input = br.readLine().trim();

            for (int c = 0; c < col; c++) {
                map[r][c] = input.charAt(c);

                // 도영이 위치
                if (map[r][c] == 'I') {
                    queue.add(new int[] { r, c });
                    visited[r][c] = true;
                }
            }
        }

        // 풀이
        int nextR, nextC;
        int[] now;

        while (!queue.isEmpty()) {
            now = queue.remove();

            for (int i = 0; i < 4; i++) {
                nextR = now[0] + dR[i];
                nextC = now[1] + dC[i];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if (!visited[nextR][nextC] && map[nextR][nextC] != 'X') {
                    queue.add(new int[] { nextR, nextC });
                    visited[nextR][nextC] = true;

                    if (map[nextR][nextC] == 'P') {
                        res++;
                    }
                }
            }
        }

        // 출력
        if (res > 0) {
            sb.append(res);
        } else {
            sb.append("TT");
        }

        System.out.println(sb);
    }
}
