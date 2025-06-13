import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col, len;
    static char map[][];
    static boolean[][] visited;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        visited = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            String str = br.readLine().trim();

            for (int c = 0; c < col; c++) {
                map[r][c] = str.charAt(c);
            }
        }
    }

    static void getRes() {
        visited[row - 1][0] = true;
        dfs(row - 1, 0, 1);
    }

    static void dfs(int r, int c, int cnt) {
        if (cnt == len && r == 0 && c == col - 1) {
            res++;
            return;
        } else if (cnt > len) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = r + dR[i];
            int nextC = c + dC[i];

            if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                continue;
            }

            if (!visited[nextR][nextC] && map[nextR][nextC] == '.') {
                visited[nextR][nextC] = true;
                dfs(nextR, nextC, cnt + 1);
                visited[nextR][nextC] = false;
            }
        }
    }
}
