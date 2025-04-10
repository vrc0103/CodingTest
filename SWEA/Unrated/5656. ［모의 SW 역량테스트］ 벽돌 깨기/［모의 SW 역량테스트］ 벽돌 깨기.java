import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int num, row, col;
    static int[][] map;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes(0, map);

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        for(int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes(int cnt, int[][] raw) {
        if(cnt == num) {
            int sum = 0;

            for(int r = 0; r < row; r++) {
                for(int c = 0; c < col; c++) {
                    if(raw[r][c] > 0) {
                        sum++;
                    }
                }
            }

            res = Math.min(res, sum);

            return;
        }

        for(int sel = 0; sel < col; sel++) {
            // deep copy
            int[][] tmp = new int[row][col];

            for(int i = 0; i < row; i++) {
                for(int j = 0; j < col; j++) {
                    tmp[i][j] = raw[i][j];
                }
            }

            // 벽돌 부수기
            ArrayDeque<int[]> q = new ArrayDeque<>();

            for(int r = 0; r < row; r++) {
                if(tmp[r][sel] > 0) {
                    q.offer(new int[] {r, sel, tmp[r][sel]});
                    tmp[r][sel] = 0;

                    while(!q.isEmpty()) {
                        int[] now = q.poll();
                        int len = now[2];

                        for(int i = 1; i < len; i++) {
                            for(int j = 0; j < 4; j++) {
                                int nextR = now[0] + dR[j] * i;
                                int nextC = now[1] + dC[j] * i;

                                if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                                    continue;
                                }

                                if(tmp[nextR][nextC] > 1) {
                                    q.offer(new int[] {nextR, nextC, tmp[nextR][nextC]});
                                }

                                tmp[nextR][nextC] = 0;
                            }
                        }
                    }

                    break;
                }
            }

            // 벽돌 떨어짐
            for(int c = 0; c < col; c++) {
                int btm = row - 1;

                for(int r = row - 1; r >= 0; r--) {
                    if(tmp[r][c] > 0) {
                        tmp[btm][c] = tmp[r][c];

                        if(btm != r) {
                            tmp[r][c] = 0;
                        }

                        btm--;
                    }
                }
            }

            // 재귀
            getRes(cnt + 1, tmp);
        }
    }
}
