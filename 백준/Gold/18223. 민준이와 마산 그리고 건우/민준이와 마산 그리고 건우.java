import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int node, edge;
    static int help;
    static List<List<int[]>> map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        help = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(from).add(new int[] { to, cost });
            map.get(to).add(new int[] { from, cost });
        }
    }

    static void getRes() {
        int dijk1 = dijk(1, help);
        int dijk2 = dijk(help, node);
        int dijk = dijk(1, node);

        sb.append(dijk == dijk1 + dijk2 ? "SAVE HIM" : "GOOD BYE");
    }

    static int dijk(int start, int end) {
        int[] cost = new int[node + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        Arrays.fill(cost, Integer.MAX_VALUE);

        pq.offer(new int[] { start, 0 });
        cost[start] = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowN = now[0];
            int nowC = now[1];

            if (cost[nowN] < nowC) {
                continue;
            }

            for (int[] next : map.get(nowN)) {
                int nextN = next[0];
                int nextC = nowC + next[1];

                // 동일한 비용이어도 건우를 구할 수 있는지 확인하기 위해 탐색 수행
                if (cost[nextN] >= nextC) {
                    pq.offer(new int[] { nextN, nextC });
                    cost[nextN] = nextC;
                }
            }
        }

        return cost[end];
    }
}
