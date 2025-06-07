import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int node, edge, loc;
    static List<List<int[]>> map;
    static List<List<int[]>> reverse;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        loc = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            map.add(new ArrayList<>());
        }

        reverse = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            reverse.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(from).add(new int[] { to, cost });
            reverse.get(to).add(new int[] { from, cost });
        }
    }

    static void getRes() {
        int[] come = new int[node + 1];
        int[] back = new int[node + 1];

        Arrays.fill(come, 987654321);
        dijkstra(come, reverse);

        Arrays.fill(back, 987654321);
        dijkstra(back, map);

        int sum = 0;
        for (int i = 1; i <= node; i++) {
            sum = come[i] + back[i];

            res = Math.max(res, sum);
        }
    }

    static void dijkstra(int[] cost, List<List<int[]>> graph) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        pq.offer(new int[] { loc, 0 });
        cost[loc] = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (cost[now[0]] < now[1]) {
                continue;
            }

            cost[now[0]] = now[1];

            for (int[] next : graph.get(now[0])) {
                int nextLoc = next[0];
                int nextCost = now[1] + next[1];

                if (cost[nextLoc] > nextCost) {
                    cost[nextLoc] = nextCost;
                    pq.offer(new int[] { nextLoc, nextCost });
                }
            }
        }
    }
}
