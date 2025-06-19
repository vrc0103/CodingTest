import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int node, edge;
    static int start, end;
    static List<List<Edge>> map;

    static class Edge {
        int num;
        int cost;

        public Edge(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

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

        map = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(num1).add(new Edge(num2, cost));
            map.get(num2).add(new Edge(num1, cost));
        }

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void getRes() {
        int[] cost = new int[node + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);

        pq.offer(new Edge(start, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (cost[now.num] > now.cost) {
                continue;
            }

            cost[now.num] = now.cost;

            for (Edge next : map.get(now.num)) {
                int nextCost = Math.min(now.cost, next.cost);

                if (cost[next.num] < nextCost) {
                    cost[next.num] = nextCost;
                    pq.offer(new Edge(next.num, nextCost));
                }
            }
        }

        res = cost[end];
    }
}
