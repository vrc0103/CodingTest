import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int size;
    static int[][] map;
    static long[][] cnt;

    static int[] dR = {0, 1};
    static int[] dC = {1, 0};

    public static void main(String[] args) throws Exception {
        // 정보
        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];
        cnt = new long[size][size];

        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 풀이
        int len;
        int nextR, nextC;
        int[] now;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[0] + o1[1]) - (o2[0] + o2[1]));

        pq.add(new int[] {0, 0});
        cnt[0][0] = 1;

        while(!pq.isEmpty()) {
            now = pq.remove();

            len = map[now[0]][now[1]];

            for(int i = 0; i < 2; i++) {
                nextR = now[0] + len * dR[i];
                nextC = now[1] + len * dC[i];

                if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                    continue;
                }

                if(cnt[nextR][nextC] == 0 && map[nextR][nextC] > 0) {
                    pq.add(new int[] {nextR, nextC});
                }

                cnt[nextR][nextC] += cnt[now[0]][now[1]];
            }
        }

        System.out.println(cnt[size - 1][size - 1]);
    }
}
