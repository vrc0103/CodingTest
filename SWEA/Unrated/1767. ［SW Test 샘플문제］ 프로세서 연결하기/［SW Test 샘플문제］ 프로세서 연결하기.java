import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int max;
    static int size;
    static int[][] map;
    static ArrayList<int[]> loc;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        max = 0;
        res = 100_000_000;
        loc = new ArrayList<>();
        size = Integer.parseInt(br.readLine().trim());

        map = new int[size][size];
        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] == 1) {
                    loc.add(new int[] { r, c });
                }
            }
        }
    }

    static void getRes(int cnt, int linked, int len) {
        if (cnt == loc.size()) {
            // System.out.printf("%d %d %d\n", cnt, linked, len);
            // for(int[] tmp : map) {
            // System.out.println(Arrays.toString(tmp));
            // }
            // System.out.println();
            if (max < linked) {
                max = linked;
                res = len;
            } else if (max == linked) {
                res = Math.min(res, len);
            }

            return;
        }

        int[] now = loc.get(cnt);

        if (now[0] == 0 || now[0] == size - 1 || now[1] == 0 || now[1] == size - 1) {
            getRes(cnt + 1, linked + 1, len);

            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            boolean pos = true;

            int nextR = now[0] + dR[dir];
            int nextC = now[1] + dC[dir];

            while (nextR >= 0 && nextR < size && nextC >= 0 && nextC < size) {
                if (map[nextR][nextC] == 1) {
                    pos = false;
                    break;
                }
                nextR += dR[dir];
                nextC += dC[dir];
            }

            // 연결 불가
            if (!pos) {
                getRes(cnt + 1, linked, len);
            }
            // 연결 가능
            else {
                // System.out.printf("(%d, %d), (%d, %d)\n", nextR, nextC, now[0], now[1]);
                int add = Math.abs(nextR - now[0]) + Math.abs(nextC - now[1]) - 1;

                while (nextR != now[0] || nextC != now[1]) {
                    nextR -= dR[dir];
                    nextC -= dC[dir];

                    map[nextR][nextC] = 1;
                }

                getRes(cnt + 1, linked + 1, len + add);

                nextR += dR[dir];
                nextC += dC[dir];

                while (nextR >= 0 && nextR < size && nextC >= 0 && nextC < size) {
                    map[nextR][nextC] = 0;

                    nextR += dR[dir];
                    nextC += dC[dir];
                }
            }
        }
    }
}
