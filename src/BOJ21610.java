import java.io.*;
import java.util.*;

public class BOJ21610 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int size;
    static int count;
    static int[][] bucket;
    static Queue<int[]> cloud = new LinkedList<>();
    static Queue<int[]> bug = new LinkedList<>();

    static int[] dR = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dC = { -1, -1, 0, 1, 1, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        getBucket();

        getWater();

        System.out.println(res);
    }

    static void getBucket() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        size = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        bucket = new int[size][size];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                bucket[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getWater() throws IOException {
        int dir, spd;
        int nextR, nextC;
        int[] now;
        boolean[][] selected;

        // 첫 구름 위치
        cloud.add(new int[] { size - 1, 0 });
        cloud.add(new int[] { size - 2, 0 });
        cloud.add(new int[] { size - 1, 1 });
        cloud.add(new int[] { size - 2, 1 });

        for (int cnt = 0; cnt < count; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            dir = Integer.parseInt(st.nextToken()) - 1; // 1 ~ 8로 입력됨
            spd = Integer.parseInt(st.nextToken());
            selected = new boolean[size][size];

            // 구름 이동 및 비 내림
            while (!cloud.isEmpty()) {
                now = cloud.poll();
                nextR = (now[0] + dR[dir] * spd) % size;
                nextC = (now[1] + dC[dir] * spd) % size;

                if (nextR < 0) {
                    nextR += size;
                }
                if (nextC < 0) {
                    nextC += size;
                }

                bucket[nextR][nextC]++;

                // 물복사버그 위치 저장
                bug.add(new int[] { nextR, nextC });

                // 구름 사라진 위치
                selected[nextR][nextC] = true;
            }

            // 물복사
            while (!bug.isEmpty()) {
                now = bug.poll();

                for (int i = 0; i < 4; i++) {
                    nextR = now[0] + dR[2 * i + 1];
                    nextC = now[1] + dC[2 * i + 1];

                    if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                        continue;
                    }

                    if (bucket[nextR][nextC] > 0) {
                        bucket[now[0]][now[1]]++;
                    }
                }
            }

            // 구름 생성
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (!selected[r][c] && bucket[r][c] >= 2) {
                        bucket[r][c] -= 2;
                        cloud.add(new int[] { r, c });
                    }
                }
            }
        }

        res = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                res += bucket[r][c];
            }
        }
    }
}
