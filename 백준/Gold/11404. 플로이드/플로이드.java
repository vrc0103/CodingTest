import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numN, numE;
    static int[][] map;
    static int[][] res;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 정보
        numN = Integer.parseInt(br.readLine().trim());
        numE = Integer.parseInt(br.readLine().trim());

        map = new int[numN + 1][numN + 1];

        for(int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(map[from][to] == 0) {
                map[from][to] = cost;
            } else {
                map[from][to] = Math.min(map[from][to], cost);
            }
        }

        // for(int[] tmp : map) System.out.println(Arrays.toString(tmp));

        // 풀이
        res = new int[numN + 1][numN + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);   // 1번 idx : cost

        // 각 도시마다 다익스트라
        for(int start = 1; start <= numN; start++) {
            pq.clear();

            Arrays.fill(res[start], 100_000_000);
            res[start][start] = 0;

            for(int i = 1; i <= numN; i++) {
                if(i != start && map[start][i] > 0) {
                    pq.add(new int[] {i, map[start][i]});
                }
            }

            while(!pq.isEmpty()) {
                int[] now = pq.remove();
                int dest = now[0];
                int cost = now[1];

                if(res[start][dest] <= cost) {
                    continue;
                }

                res[start][dest] = cost;

                // System.out.printf("start : %d  ,  dest : %d  ,  cost : %d\n", start, dest, cost);

                for(int i = 1; i <= numN; i++) {
                    if(i == start || i == dest || map[dest][i] == 0) {
                        continue;
                    }

                    int nextCost = cost + map[dest][i];

                    if(res[start][i] > nextCost) {
                        pq.add(new int[] {i, nextCost});
                    }
                }
            }
        }

        for(int r = 1; r <= numN; r++) {
            for(int c = 1; c <= numN; c++) {
                sb.append(res[r][c] == 100_000_000 ? 0 : res[r][c]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}