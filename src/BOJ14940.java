import java.io.*;
import java.util.*;

public class BOJ14940 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int row, col;
    static int[][] map;
    static int[] goal;

    static int dR[] = { 0, 1, 0, -1 };
    static int dC[] = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(sb.toString());
    }

    static void setInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] == 2) {
                    goal = new int[] { r, c };
                }
            }
        }
    }

    static void getRes() {
        int nowR, nowC;
        int nextR, nextC;
        int count = 0;
        int size;
        int[][] len = new int[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (map[r][c] > 0) {
                    len[r][c] = -1;
                }
            }
        }

        queue.add(goal);
        len[goal[0]][goal[1]] = 0;

        while (!queue.isEmpty()) {
            size = queue.size();

            count++;

            // 동일한 회차별로 끊어서 경로 계산
            for (int i = 0; i < size; i++) {
                nowR = queue.peek()[0];
                nowC = queue.peek()[1];
                queue.remove();

                for (int j = 0; j < 4; j++) {
                    nextR = nowR + dR[j];
                    nextC = nowC + dC[j];

                    // 배열 범위
                    if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                        continue;
                    }

                    // 이미 계산된 칸
                    if (len[nextR][nextC] >= 0) {
                        continue;
                    }

                    // 아직 계산 안된 칸이면 길이 저장, 벽이면 0
                    if (map[nextR][nextC] == 1) {
                        len[nextR][nextC] = count;
                        queue.add(new int[] { nextR, nextC });
                    } else if (map[nextR][nextC] == 0) {
                        len[nextR][nextC] = 0;
                    }
                }
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                sb.append(len[r][c]).append(" ");
            }

            if (r < row - 1) {
                sb.append("\n");
            }
        }
    }
}
