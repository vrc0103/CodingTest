import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int size;
    static char[][] map;

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
        size = Integer.parseInt(br.readLine().trim());

        map = new char[size][];
        for (int r = 0; r < size; r++) {
            map[r] = br.readLine().trim().toCharArray();
        }
    }

    static void getRes() {
        boolean[][] visited = new boolean[size][size];
        int cnt = 0;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (!visited[r][c]) {
                    char color = map[r][c];
                    ArrayDeque<int[]> q = new ArrayDeque<>();

                    cnt++;
                    visited[r][c] = true;
                    q.offer(new int[] { r, c });
                    while (!q.isEmpty()) {
                        int[] now = q.poll();

                        for (int i = 0; i < 4; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                                continue;
                            }

                            if (!visited[nextR][nextC] && map[nextR][nextC] == color) {
                                visited[nextR][nextC] = true;
                                q.offer(new int[] { nextR, nextC });
                            }
                        }
                    }
                }
            }
        }

        sb.append(cnt).append(" ");

        visited = new boolean[size][size];
        cnt = 0;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (!visited[r][c]) {
                    boolean isBlue = map[r][c] == 'B' ? true : false;
                    ArrayDeque<int[]> q = new ArrayDeque<>();

                    cnt++;
                    visited[r][c] = true;
                    q.offer(new int[] { r, c });
                    while (!q.isEmpty()) {
                        int[] now = q.poll();

                        for (int i = 0; i < 4; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                                continue;
                            }

                            if (!visited[nextR][nextC]) {
                                if (isBlue && map[nextR][nextC] == 'B') {
                                    visited[nextR][nextC] = true;
                                    q.offer(new int[] { nextR, nextC });
                                } else if (!isBlue && map[nextR][nextC] != 'B') {
                                    visited[nextR][nextC] = true;
                                    q.offer(new int[] { nextR, nextC });
                                }
                            }
                        }
                    }
                }
            }
        }

        sb.append(cnt).append("\n");
    }
}
