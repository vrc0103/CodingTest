import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int row, col;
    static char[][] map;
    static int[][] cluster;
    static ArrayDeque<Integer> cmd;

    static int[] dR = { 1, 0, -1, 0 };
    static int[] dC = { 0, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        for (int r = 0; r < row; r++) {
            String input = br.readLine().trim();

            for (int c = 0; c < col; c++) {
                map[r][c] = input.charAt(c);
            }
        }

        br.readLine().trim();

        cmd = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine().trim());
        while (st.hasMoreTokens()) {
            cmd.offer(Integer.parseInt(st.nextToken()));
        }
    }

    static void getRes() {
        // 투창 -> 미네랄 파괴 -> 클러스터 하강
        boolean isLeft = false;

        while (!cmd.isEmpty()) {
            int h = row - cmd.poll();
            isLeft = !isLeft;

            if (isLeft) {
                for (int c = 0; c < col; c++) {
                    if (map[h][c] == 'x') {
                        map[h][c] = '.';
                        down(h, c, isLeft);
                        break;
                    }
                }
            } else {
                for (int c = col - 1; c >= 0; c--) {
                    if (map[h][c] == 'x') {
                        map[h][c] = '.';
                        down(h, c, isLeft);
                        break;
                    }
                }
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
    }

    static void down(int r, int c, boolean isLeft) {
        int next = isLeft ? 1 : -1;
        ArrayDeque<int[]> search = new ArrayDeque<>();
        ArrayDeque<int[]> moved = new ArrayDeque<>();

        // 두 개 이상의 클러스터가 동시에 떨어지지 않음 -> 아래 세 분기 중 하나만 성공함
        if (c < col - 1 && c > 0 && map[r][c + next] == 'x' && !ground(r, c + next)) {
            search.offer(new int[] { r, c + next });
            map[r][c + next] = '.';
        }

        if (r > 0 && map[r - 1][c] == 'x' && !ground(r - 1, c)) {
            search.offer(new int[] { r - 1, c });
            map[r - 1][c] = '.';
        }

        if (r < row - 1 && map[r + 1][c] == 'x' && !ground(r + 1, c)) {
            search.offer(new int[] { r + 1, c });
            map[r + 1][c] = '.';
        }

        while (!search.isEmpty()) {
            int[] now = search.poll();

            // 미네랄 위치
            moved.offer(now);

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if (map[nextR][nextC] == 'x') {
                    search.offer(new int[] { nextR, nextC });
                    map[nextR][nextC] = '.';
                }
            }
        }

        if (!moved.isEmpty()) {
            int size = moved.size();
            boolean pos = true;

            while (pos) {
                // 클러스터 단위로 처리
                for (int i = 0; i < size; i++) {
                    int[] now = moved.poll();

                    int checkR = now[0] + 2;
                    int checkC = now[1];

                    // 2칸 아래 확인해야 1칸 내려가고 멈출 수 있음
                    if (checkR >= row || map[checkR][checkC] == 'x') {
                        pos = false;
                    }

                    // 1칸 하강
                    now[0] += 1;
                    moved.offer(now);
                }
            }
        }

        // 큐에 저장된 미네랄들 다시 배치
        while (!moved.isEmpty()) {
            int[] now = moved.poll();

            map[now[0]][now[1]] = 'x';
        }
    }

    static boolean ground(int r, int c) {
        boolean[][] visited = new boolean[row][col];
        ArrayDeque<int[]> search = new ArrayDeque<>();

        search.offer(new int[] { r, c });
        visited[r][c] = true;

        while (!search.isEmpty()) {
            int[] now = search.poll();

            // 땅에 닿아있는 클러스터인지 판단
            if (now[0] == row - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if (map[nextR][nextC] == 'x' && !visited[nextR][nextC]) {
                    search.offer(new int[] { nextR, nextC });
                    visited[nextR][nextC] = true;
                }
            }
        }

        return false;
    }
}
