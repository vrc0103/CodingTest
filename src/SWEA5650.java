import java.io.*;
import java.util.*;

public class SWEA5650 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int size;
    static int max;
    static int[][] map;
    static ArrayList<ArrayList<Integer>> wormhole;

    // 오른쪽부터 시계방향
    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            getMap();
            getScore();
            sb.append(String.format("#%d %d\n", tc, max));
        }

        System.out.print(sb);
    }

    static void getMap() throws IOException {
        max = 0;
        wormhole = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            wormhole.add(new ArrayList<>());
        }

        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] >= 6) {
                    wormhole.get(map[r][c] - 6).add(r);
                    wormhole.get(map[r][c] - 6).add(c);
                }
            }
        }
    }

    static void getScore() {
        // 시작 지점, 방향 결정
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (map[r][c] == 0) {
                    for (int d = 0; d < 4; d++) {
                        // System.out.printf("start at %d %d %d\n", r, c, d);
                        computeScore(r, c, d);
                    }
                }
            }
        }
    }

    static void computeScore(int row, int col, int dir) {
        int nowR = row;
        int nowC = col;
        int nextR, nextC;
        int score = 0;
        ArrayList<Integer> tmpArr;

        while (true) {
            nextR = nowR + dR[dir];
            nextC = nowC + dC[dir];

            // 경계에 도달 시 방향 변경
            if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                dir = (dir + 2) % 4;
                nowR = nextR;
                nowC = nextC;
                score++;
                continue;
            }

            // 벽
            else if (map[nextR][nextC] >= 1 && map[nextR][nextC] <= 5) {
                dir = changeDir(nextR, nextC, dir);
                score++;
            }

            // 웜홀
            else if (map[nextR][nextC] >= 6) {
                tmpArr = wormhole.get(map[nextR][nextC] - 6);
                if (nextR == tmpArr.get(0) && nextC == tmpArr.get(1)) {
                    nextR = tmpArr.get(2);
                    nextC = tmpArr.get(3);
                } else {
                    nextR = tmpArr.get(0);
                    nextC = tmpArr.get(1);
                }
            }

            // 게임 종료
            if ((nextR == row && nextC == col) || map[nextR][nextC] == -1) {
                max = Math.max(max, score);
                break;
            }

            nowR = nextR;
            nowC = nextC;
        }
    }

    static int changeDir(int row, int col, int dir) {
        // 블럭 - 방향 전환
        // 1(0) : 2->3 , 1->0
        // 2(1) : 3->0 , 2->1
        // 3(2) : 0->1 , 3->2
        // 4(3) : 1->2 , 0->3
        // 5 및 위에 해당하지 않는 경우는 반대로 튕김

        int block = map[row][col] - 1;

        if (block == (dir + 2) % 4) {
            dir = (dir + 1) % 4;
        } else if (block == (dir + 3) % 4) {
            dir = (dir + 3) % 4;
        } else {
            dir = (dir + 2) % 4;
        }

        return dir;
    }
}
