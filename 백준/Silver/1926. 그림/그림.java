import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int cnt, res;
    static int row, col;
    static int[][] map;
    static boolean[][] checked;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.printf("%d\n%d\n", cnt, res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        checked = new boolean[row][col];

        for(int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int area;
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(map[r][c] == 1 && !checked[r][c]) {
                    cnt++;

                    area = 1;
                    checked[r][c] = true;
                    q.offer(new int[] {r, c});

                    while(!q.isEmpty()) {
                        int[] now = q.poll();

                        for(int i = 0; i < 4; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                                continue;
                            }

                            if(map[nextR][nextC] == 1 && !checked[nextR][nextC]) {
                                area++;
                                checked[nextR][nextC] = true;
                                q.offer(new int[] {nextR, nextC});
                            }
                        }
                    }

                    res = Math.max(res, area);
                }
            }
        }
    }
}
