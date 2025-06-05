import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int d = 0; d < 4; d++) {
            move(map, d, 0);
        }
    }

    static void move(int[][] raw, int dir, int cnt) {
        if (cnt == 5) {
            int max = 0;

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    max = Math.max(max, raw[r][c]);
                }
            }

            res = Math.max(res, max);

            return;
        }

        int[][] tmp = new int[size][size];
        boolean[][] merged = new boolean[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                tmp[r][c] = raw[r][c];
            }
        }

        if (dir == 0) { // 왼쪽
            for (int r = 0; r < size; r++) {
                for (int c = 1; c < size; c++) {
                    if (tmp[r][c] == 0) {
                        continue;
                    }

                    // 진행 방향에 있는 숫자 탐색
                    int t = c;
                    while (t > 0 && tmp[r][t - 1] == 0) {
                        t--;
                    }

                    // 같은 숫자면 병합
                    if (t > 0 && tmp[r][t - 1] == tmp[r][c] && !merged[r][t - 1]) {
                        merged[r][t - 1] = true;
                        tmp[r][t - 1] *= 2;
                        tmp[r][c] = 0;
                    }
                    // 다른 숫자면 이동, 다른 열로 이동시킬 때만 수행
                    else if (t != c) {
                        tmp[r][t] = tmp[r][c];
                        tmp[r][c] = 0;
                    }
                }
            }
        } else if (dir == 1) { // 오른쪽
            for (int r = 0; r < size; r++) {
                for (int c = size - 2; c >= 0; c--) {
                    if (tmp[r][c] == 0) {
                        continue;
                    }

                    int t = c;
                    while (t < size - 1 && tmp[r][t + 1] == 0) {
                        t++;
                    }

                    if (t < size - 1 && tmp[r][t + 1] == tmp[r][c] && !merged[r][t + 1]) {
                        merged[r][t + 1] = true;
                        tmp[r][t + 1] *= 2;
                        tmp[r][c] = 0;
                    } else if (t != c) {
                        tmp[r][t] = tmp[r][c];
                        tmp[r][c] = 0;
                    }
                }
            }
        } else if (dir == 2) { // 위
            for (int c = 0; c < size; c++) {
                for (int r = 1; r < size; r++) {
                    if (tmp[r][c] == 0) {
                        continue;
                    }

                    int t = r;
                    while (t > 0 && tmp[t - 1][c] == 0) {
                        t--;
                    }

                    if (t > 0 && tmp[t - 1][c] == tmp[r][c] && !merged[t - 1][c]) {
                        merged[t - 1][c] = true;
                        tmp[t - 1][c] *= 2;
                        tmp[r][c] = 0;
                    } else if (t != r) {
                        tmp[t][c] = tmp[r][c];
                        tmp[r][c] = 0;
                    }
                }
            }
        } else { // 아래
            for (int c = 0; c < size; c++) {
                for (int r = size - 2; r >= 0; r--) {
                    if (tmp[r][c] == 0) {
                        continue;
                    }

                    int t = r;
                    while (t < size - 1 && tmp[t + 1][c] == 0) {
                        t++;
                    }

                    if (t < size - 1 && tmp[t + 1][c] == tmp[r][c] && !merged[t + 1][c]) {
                        merged[t + 1][c] = true;
                        tmp[t + 1][c] *= 2;
                        tmp[r][c] = 0;
                    } else if (t != r) {
                        tmp[t][c] = tmp[r][c];
                        tmp[r][c] = 0;
                    }
                }
            }
        }

        for (int d = 0; d < 4; d++) {
            move(tmp, d, cnt + 1);
        }

    }
}
