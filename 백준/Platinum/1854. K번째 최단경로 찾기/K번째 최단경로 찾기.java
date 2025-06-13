import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int node, edge, K;
    static List<List<int[]>> graph;
    static List<PriorityQueue<Integer>> route;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        graph = new ArrayList<>();
        route = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
            route.add(new PriorityQueue<>((o1, o2) -> o2 - o1));
        }

        route.get(1).add(0);

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[] { to, cost });
        }
    }

    static void getRes() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        pq.offer(new int[] { 1, 0 });
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            // 기존 경로 개수가 K 이상이면서, 이번 경로 비용이 더 크면 패스
            if (route.get(now[0]).size() >= K && route.get(now[0]).peek() < now[1]) {
                continue;
            }

            for (int[] next : graph.get(now[0])) {
                int nNode = next[0];
                int nCost = next[1] + now[1];
                PriorityQueue<Integer> nRoute = route.get(nNode);

                if (nRoute.size() < K) {
                    nRoute.offer(nCost);
                    pq.offer(new int[] { nNode, nCost });
                } else if (nRoute.peek() > nCost) {
                    // K개의 경로가 저장되어있을 때 새로운 경로가 기존 경로보다 작으면 갱신
                    nRoute.poll();
                    nRoute.offer(nCost);
                    pq.offer(new int[] { nNode, nCost });
                }
            }
        }

        for (int i = 1; i <= node; i++) {
            if (route.get(i).size() == K) {
                sb.append(route.get(i).peek());
            } else {
                sb.append(-1);
            }

            sb.append("\n");
        }
    }
}
