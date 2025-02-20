import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 정보
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int[][] map = new int[row][col];

        for(int r = 0; r < row; r++) {
            String input = br.readLine().trim();

            for(int c = 0; c < col; c++) {
                map[r][c] = input.charAt(c) - '0';
            }
        }

        // 풀이
        int nextR, nextC;
        int[] now;
        int[][] count = new int[row][col];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        int[] dR = {0, 1, 0, -1};
        int[] dC = {1, 0, -1, 0};

        for(int i = 0; i < row; i++) {
            Arrays.fill(count[i], 10000);
        }

        count[0][0] = 0;
        pq.add(new int[] {0, 0, 0});

        while(true) {
            now = pq.remove();

            if(now[0] == row - 1 && now[1] == col - 1) {
                break;
            }

            for(int i = 0; i < 4; i++) {
                nextR = now[0] + dR[i];
                nextC = now[1] + dC[i];

                if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if(now[2] + map[nextR][nextC] < count[nextR][nextC]) {
                    count[nextR][nextC] = now[2] + map[nextR][nextC];
                    pq.add(new int[] {nextR, nextC, now[2] + map[nextR][nextC]});
                }

                // for(int[] tmp : count) {
                //     System.out.println(Arrays.toString(tmp));
                // }
                // System.out.println();
            }
        }

        System.out.println(count[row - 1][col - 1]);
    }
}
