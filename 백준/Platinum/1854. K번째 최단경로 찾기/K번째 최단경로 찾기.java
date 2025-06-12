import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int node, edge, K;
    static List<List<int[]>> graph;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        graph = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[] { to, cost });
        }
    }

    static void getRes() {
        int[] cnt = new int[node + 1];
        int[] cost = new int[node + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        pq.offer(new int[] { 1, 0 });
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (cnt[now[0]] >= K) {
                continue;
            }

            cost[now[0]] = now[1];
            cnt[now[0]]++;

            for (int[] next : graph.get(now[0])) {
                int nNode = next[0];
                int nCost = next[1] + now[1];

                if (cnt[nNode] < K) {
                    pq.offer(new int[] { nNode, nCost });
                }
            }
        }

        for (int i = 1; i <= node; i++) {
            if (cnt[i] == K) {
                sb.append(cost[i]);
            } else {
                sb.append(-1);
            }

            sb.append("\n");
        }
    }
}
