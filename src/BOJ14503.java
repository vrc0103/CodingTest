/*
 * map : 0이면 청소되지 않은 칸, 1이면 벽
 * 청소기 작동
 *  1) 청소되지 않은 칸 청소
 *  2) 현재 위치 기준으로 사방이 모두 깨끗하면 방향을 유지하고 후진(후진 불가능 시 작동 정지)
 *  3) 청소할 칸이 있으면 반시계 방향으로 회전하면서 청소할 칸으로 이동
 */

import java.io.*;
import java.util.*;

public class BOJ14503 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int row, col;
    static int startR, startC, startD;
    static int[][] map;
    static boolean[][] cleaned;

    static int[] dR = { -1, 0, 1, 0 };
    static int[] dC = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        getInfo();

        getRes();

        System.out.println(res);
    }

    static void getInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        startR = Integer.parseInt(st.nextToken());
        startC = Integer.parseInt(st.nextToken());
        startD = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        cleaned = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int nowR = startR;
        int nowC = startC;
        int nowD = startD;
        int nextR, nextC, nextD;

        res = 0;

        while (true) {
            // 1. 현재 칸을 청소하지 않았으면 청소
            if (!cleaned[nowR][nowC]) {
                res++;
                cleaned[nowR][nowC] = true;
            }

            for (int i = 1; i <= 4; i++) {
                // 3-1. 반시계 방향으로 주변 칸 탐색
                nextD = (nowD + 3 * i) % 4;
                nextR = nowR + dR[nextD];
                nextC = nowC + dC[nextD];

                // 3-2. 청소하지 않은 빈 칸이 존재하면 해당 칸으로 이동
                if (map[nextR][nextC] == 0 && !cleaned[nextR][nextC]) {
                    nowR = nextR;
                    nowC = nextC;
                    nowD = nextD;

                    break;
                }

                // 2-1. 주변 네 칸이 모두 청소된 경우 한 칸 후진
                if (i == 4) {
                    nowR = nowR - dR[nowD];
                    nowC = nowC - dC[nowD];

                    // 2-2. 후진한 칸이 벽이면 작동 중지
                    if (map[nowR][nowC] == 1) {
                        return;
                    }
                }
            }
        }
    }
}
