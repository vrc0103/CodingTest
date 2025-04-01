import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringBuilder sb;

    static int res;
    static int size;
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
        size = Integer.parseInt(br.readLine().trim());

        map = new int[size][size];
        for(int r = 0; r < size; r++) {
            String str = br.readLine().trim();

            for(int c = 0; c < size; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }
    }

    static void getRes() {
        boolean[][] visited = new boolean[size][size];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        pq.offer(new int[] {0, 0, 0});

        while(true) {
            int[] now = pq.poll();
            
            if(now[0] == size - 1 && now[1] == size - 1) {
                res = now[2];

                break;
            }

            visited[now[0]][now[1]] = true;

            for(int i = 0; i < 4; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];

                if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                    continue;
                }

                int nextV = now[2] + map[nextR][nextC];

                // 아직 확정 안된 위치
                if(!visited[nextR][nextC]) {
                    pq.offer(new int[] {nextR, nextC, nextV});
                }
            }
        }
    }
}
