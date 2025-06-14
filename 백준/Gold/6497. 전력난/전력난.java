import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static long res;
    static long max;
    static int node, edge;
    static int[] root;
    static boolean isEnd;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {
        int n1, n2;
        long cost;

        public Edge(int n1, int n2, long cost) {
            this.n1 = n1;
            this.n2 = n2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.cost, e.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            setInfo();

            if (isEnd) {
                break;
            }

            getRes();

            sb.append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        max = 0;

        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        if (node == 0 && edge == 0) {
            isEnd = true;
            return;
        }

        pq = new PriorityQueue<>();

        root = new int[node];
        for (int i = 0; i < node; i++) {
            root[i] = i;
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            pq.offer(new Edge(n1, n2, cost));

            max += cost;
        }
    }

    static void getRes() {
        long sum = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (union(now.n1, now.n2)) {
                sum += now.cost;
            }
        }

        res = max - sum;
    }

    static boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 == p2) {
            // 사이클 감지
            return false;
        } else if (p1 > p2) {
            root[p1] = p2;
        } else {
            root[p2] = p1;
        }

        return true;
    }

    static int find(int n) {
        if (root[n] == n) {
            return n;
        }

        return root[n] = find(root[n]);
    }
}
