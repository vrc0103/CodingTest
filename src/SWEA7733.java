import java.io.*;
import java.util.*;

public class SWEA7733 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int size;
    static int maxTaste;
    static int[][] cheese;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            getCheese();

            sb.append(String.format("#%d %d\n", tc, countCheese()));
        }

        System.out.print(sb);
    }

    static void getCheese() throws IOException {
        maxTaste = 0;

        size = Integer.parseInt(br.readLine().trim());
        cheese = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int j = 0; j < size; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
                maxTaste = Math.max(cheese[i][j], maxTaste);
            }
        }
    }

    static int countCheese() {
        int[] count = new int[maxTaste + 1];
        boolean[][] eat;

        for (int day = 0; day <= maxTaste; day++) {
            eat = new boolean[size][size];

            for (int i = 0; i < size; i++) { // 요정이 먹은 치즈
                for (int j = 0; j < size; j++) {
                    if (cheese[i][j] <= day) {
                        eat[i][j] = true;
                    }
                }
            }

            count[day] = BFS(eat);
        }

        Arrays.sort(count);

        return count[maxTaste];
    }

    static int BFS(boolean[][] eat) {
        int count = 0;
        int nextR, nextC;
        int[] now = new int[2];
        int[] dirR = { 0, -1, 0, 1 };
        int[] dirC = { 1, 0, -1, 0 };
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // 아직 먹지 않은 치즈 덩어리 개수 탐색
                if (!eat[i][j]) {
                    queue.offer(new int[] { i, j });
                    eat[i][j] = true;

                    while (!queue.isEmpty()) {
                        now = queue.poll();

                        for (int idx = 0; idx < 4; idx++) {
                            nextR = now[0] + dirR[idx];
                            nextC = now[1] + dirC[idx];

                            if (nextR < 0 || nextR == size || nextC < 0 || nextC == size) {
                                continue;
                            }

                            if (!eat[nextR][nextC]) {
                                queue.offer(new int[] { nextR, nextC });
                                eat[nextR][nextC] = true;
                            }
                        }
                    }

                    count++;
                }
            }
        }

        return count;
    }
}
