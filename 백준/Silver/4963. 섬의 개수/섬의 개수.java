import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int row, col;
    static int[][] map;
    static int[][] isle;

    static int[] dR = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int[] dC = { 1, 1, 0, -1, -1, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            if (!setInfo()) {
                break;
            }

            getRes();

            sb.append(res).append("\n");
        }

        System.out.print(sb);
    }

    static boolean setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        if (row == 0 && col == 0) {
            return false;
        }

        map = new int[row][col];
        isle = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        return true;
    }

    static void getRes() {
        int idx = 0;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (map[r][c] == 1 && isle[r][c] == 0) {
                    ArrayDeque<int[]> q = new ArrayDeque<>();

                    q.offer(new int[] { r, c });
                    isle[r][c] = ++idx;
                    while (!q.isEmpty()) {
                        int[] now = q.poll();

                        for (int i = 0; i < 8; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                                continue;
                            }

                            // 아직 병합되지 않은 땅
                            if (map[nextR][nextC] == 1 && isle[nextR][nextC] == 0) {
                                q.offer(new int[] { nextR, nextC });
                                isle[nextR][nextC] = idx;
                            }
                        }
                    }
                }
            }
        }

        res = idx;
    }
}
