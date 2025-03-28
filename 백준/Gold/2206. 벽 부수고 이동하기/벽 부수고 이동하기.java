/*
 * N x M 크기의 지도
 * (0, 0)에서 (N-1, M-1)까지 이동
 * 이동 거리는 출발지점, 도착지점을 모두 포함시킴
 * 이동 중에 벽을 1개까지 부술 수 있음
 */

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int row, col;
    static int res;
    static int[][] map;

    static int[] dirR = { 0, 1, 0, -1 };
    static int[] dirC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        getMap();

        res = countLen();

        System.out.println(res);
    }

    static void getMap() throws IOException {
        String tmp;
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            tmp = br.readLine();

            for (int j = 0; j < col; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
    }

    // 가중치가 없는 최단 경로 문제는 BFS로 푸는게 좋음
    static int countLen() {
        int[] loc = new int[3];
        int nextR, nextC;
        Queue<int[]> queue = new LinkedList<>();
        int[][][] len = new int[2][row][col];

        queue.offer(new int[] { 0, 0, 0 }); // 시작 위치 추가
        len[0][0][0] = 1;

        if (row == 1 && col == 1) {
            return 1;
        }

        while (true) {
            loc = queue.poll();

            for (int idx = 0; idx < 4; idx++) {
                nextR = loc[1] + dirR[idx];
                nextC = loc[2] + dirC[idx];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) { // 맵 밖을 벗어나면 탐색 X
                    continue;
                }

                if (map[nextR][nextC] == 0) { // 평지
                    if (len[loc[0]][nextR][nextC] == 0) { // 아직 지나가지 않은 경로인 경우
                        queue.offer(new int[] { loc[0], nextR, nextC }); // 큐에 추가하고
                        len[loc[0]][nextR][nextC] = len[loc[0]][loc[1]][loc[2]] + 1; // 최소 경로 계산

                        if (nextR == row - 1 && nextC == col - 1) { // 도착 시 결과 반환
                            return len[loc[0]][nextR][nextC];
                        }
                    }
                } else { // 벽돌
                    if (loc[0] == 0) { // 벽을 부순 적이 없는 경우
                        queue.offer(new int[] { 1, nextR, nextC }); // 큐에 추가하고
                        len[1][nextR][nextC] = len[loc[0]][loc[1]][loc[2]] + 1; // 최소 경로 계산
                    }
                }
            }

            // 도착 지점에 도달하지 못한 경우
            if (queue.isEmpty()) {
                return -1;
            }
        }
    }
}
