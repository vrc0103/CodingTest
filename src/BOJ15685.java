import java.io.*;
import java.util.*;

public class BOJ15685 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int num;
    static boolean[][] map = new boolean[101][101];

    static int[] dX = { 1, 0, -1, 0 };
    static int[] dY = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        int x, y;
        int dir;
        int gen;

        num = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());
            gen = Integer.parseInt(st.nextToken());

            getCurve(x, y, dir, gen);
        }
    }

    static void getCurve(int x, int y, int dir, int gen) {
        int size;
        int[] pivot = new int[2];
        int[] now = new int[2];
        int[] next = new int[2];
        int[] dist = new int[2];
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> newPoints = new LinkedList<>();

        queue.add(new int[] { x, y });
        map[x][y] = true;

        // 0세대(직선)
        queue.add(new int[] { x + dX[dir], y + dY[dir] });
        map[x + dX[dir]][y + dY[dir]] = true;

        pivot[0] = x + dX[dir];
        pivot[1] = y + dY[dir];

        for (int i = 1; i <= gen; i++) {
            size = queue.size();

            for (int j = 0; j < size; j++) {
                now = queue.remove();
                queue.add(now);

                dist[0] = now[0] - pivot[0];
                dist[1] = now[1] - pivot[1];

                if (dist[0] == 0 && dist[1] == 0) {
                    continue;
                }

                // 시계방향 90도 회전
                // 축 방향에 주의
                next[0] = pivot[0] - dist[1];
                next[1] = pivot[1] + dist[0];

                // 기존 포인트와 새로운 포인트 구분
                newPoints.add(new int[] { next[0], next[1] });
                map[next[0]][next[1]] = true;
            }

            // 피벗 갱신
            pivot = newPoints.peek();

            // 드래곤커브 병합
            while (!newPoints.isEmpty()) {
                queue.add(newPoints.remove());
            }
        }
    }

    static void getRes() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    res++;
                }
            }
        }
    }
}
