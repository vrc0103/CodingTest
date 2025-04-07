import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int row, col, num;
    static boolean[][] map;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = 0;

        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());

        map = new boolean[row][col];
        while(num-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = true;
        }
    }

    static void getRes() {
        boolean[][] visited = new boolean[row][col];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(map[r][c] && !visited[r][c]) {
                    res++;
                    q.offer(new int[] {r, c});
                    visited[r][c] = true;

                    while(!q.isEmpty()) {
                        int[] now = q.poll();

                        for(int i = 0; i < 4; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                                continue;
                            }

                            if(map[nextR][nextC] && !visited[nextR][nextC]) {
                                q.offer(new int[] {nextR, nextC});
                                visited[nextR][nextC] = true;
                            }
                        }
                    }
                }
            }
        }
    }
}
