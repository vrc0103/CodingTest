import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numN, numE;
    static List<Edge> map;

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        numN = Integer.parseInt(st.nextToken());
        numE = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for (int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.add(new Edge(from, to, cost));
        }
    }

    static void getRes() {
        // Bellman-Ford
        int INF = Integer.MAX_VALUE;
        long[] dist = new long[numN + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        /*
         * 노드가 numN개
         * -> 최단 경로는 (numN - 1)개의 간선으로 구성됨
         * -> (numN - 1)번의 반복으로 최단 경로가 구성돼야 함
         */
        for (int i = 1; i < numN; i++) {
            for (Edge e : map) {
                //
                if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.cost) {
                    dist[e.to] = dist[e.from] + e.cost;
                }
            }
        }

        /*
         * 음수 사이클 판별
         * : numN번째 반복에서 최단 거리가 갱신된다 = 음수 가중치가 존재한다
         */
        boolean hasNeg = false;

        for (Edge e : map) {
            if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.cost) {
                hasNeg = true;
                break;
            }
        }

        if (hasNeg) {
            sb.append("-1").append("\n");
        } else {
            for (int i = 2; i <= numN; i++) {
                sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
            }
        }
    }
}
