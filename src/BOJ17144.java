import java.io.*;
import java.util.*;

public class BOJ17144 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int row, col, time;
    static ArrayList<Integer> cleaner;
    static int[][] map;

    static int[] dirR = { 0, 1, 0, -1 };
    static int[] dirC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        getDust();
    }

    static void getDust() throws IOException {
        int res;
        int nextR, nextC;
        int count;
        int[][] nextMap;

        st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        // 1. 초기 방 상태
        cleaner = new ArrayList<Integer>();
        map = new int[row][col];
        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < col; c++) {
                // 먼지 정보
                map[r][c] = Integer.parseInt(st.nextToken());

                // 공청기 위치
                if (map[r][c] == -1) {
                    cleaner.add(r);
                }
            }
        }

        // 2. 시간 흐름
        for (int t = 0; t < time; t++) {
            // 2-1. 확산
            nextMap = new int[row][col];

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    // 공기청정기 위치
                    if (map[r][c] == -1) {
                        nextMap[r][c] = -1;
                        continue;
                    }

                    // 먼지 확산
                    if (map[r][c] < 5) {
                        nextMap[r][c] += map[r][c];
                    } else {
                        count = 0;

                        for (int n = 0; n < 4; n++) {
                            nextR = r + dirR[n];
                            nextC = c + dirC[n];

                            // 범위 밖이거나 공기청정기 위치면 다음으로
                            if (nextR < 0 || nextR == row || nextC < 0 || nextC == col || map[nextR][nextC] == -1) {
                                continue;
                            }

                            nextMap[nextR][nextC] += map[r][c] / 5;
                            count++;
                        }

                        nextMap[r][c] += map[r][c] - map[r][c] / 5 * count;
                    }
                }
            }

            map = nextMap;

            // 2-2. 공청기 작동
            cleanAir(0);
            cleanAir(1);
        }

        // 3. 남은 먼지 계산
        res = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (map[r][c] > 0) {
                    res += map[r][c];
                }
            }
        }
        System.out.println(res);
    }

    static void cleanAir(int reverse) {
        int[][] rotR = { { -1, 0, 1, 0 }, { 1, 0, -1, 0 } };
        int[][] rotC = { { 0, 1, 0, -1 }, { 0, 1, 0, -1 } };

        int nextR, nextC;
        int nowR = cleaner.get(reverse) + (reverse == 0 ? -1 : 1);
        int nowC = 0;
        int dir = 0;

        while (true) {
            nextR = nowR + rotR[reverse][dir];
            nextC = nowC + rotC[reverse][dir];

            // 공청기 작동 완료
            if (nextR == cleaner.get(reverse) && nextC == 0) {
                map[nowR][nowC] = 0;

                return;
            }

            // 공기청정기 경계 도달 시 방향 전환
            if (dir == 0 && (nextR == 0 || nextR == row - 1)) {
                dir = 1;
            }
            if (dir == 1 && nextC == col - 1) {
                dir = 2;
            }
            if (dir == 2 && nextR == cleaner.get(reverse)) {
                dir = 3;
            }

            // 먼지 이동
            map[nowR][nowC] = map[nextR][nextC];

            nowR = nextR;
            nowC = nextC;
        }
    }
}
