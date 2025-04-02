import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int size, cost;
    static int[][] map;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = 0;
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        cost = Integer.parseInt(st.nextToken());

        map = new int[size][size];
        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                calc(r, c);
            }
        }
    }

    static void calc(int row, int col) {
        boolean[][] visited = new boolean[size][size];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] {row, col});
        visited[row][col] = true;

        int cnt = 0;

        // 짝수인 경우 중앙을 기준으로 전체를 포함시킬 수 없어서 size + 1까지 탐색
        for(int len = 1; len <= size + 1; len++) {
            int loop = q.size();

            while(loop-- > 0) {
                int[] now = q.poll();

                if(map[now[0]][now[1]] == 1) {
                    cnt++;
                }

                for(int i = 0; i < 4; i++) {
                    int nextR = now[0] + dR[i];
                    int nextC = now[1] + dC[i];

                    if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                        continue;
                    }

                    if(!visited[nextR][nextC]) {
                        visited[nextR][nextC] = true;
                        q.add(new int[] {nextR, nextC});
                    }
                }
            }

            if(cost * cnt >= len * len + (len - 1) * (len - 1)) {
                res = Math.max(res, cnt);
            }
        }
    }
}
