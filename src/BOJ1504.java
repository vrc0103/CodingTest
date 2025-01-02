import java.io.*;
import java.util.*;

public class BOJ1504 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int numN, numE;
    static int via1, via2;
    static ArrayList<ArrayList<Node>> route = new ArrayList<>();

    public static class Node implements Comparable<Node> {
        int dest;
        int dist;

        public Node(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        int from, to, dist;

        st = new StringTokenizer(br.readLine().trim());
        numN = Integer.parseInt(st.nextToken());
        numE = Integer.parseInt(st.nextToken());

        // 노드 개수만큼 생성 : 1 ~ N
        for (int i = 0; i <= numN; i++) {
            route.add(new ArrayList<Node>());
        }

        for (int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            route.get(from).add(new Node(to, dist));
            route.get(to).add(new Node(from, dist));
        }

        st = new StringTokenizer(br.readLine().trim());
        via1 = Integer.parseInt(st.nextToken());
        via2 = Integer.parseInt(st.nextToken());
    }

    static void getRes() {
        int MAX = Integer.MAX_VALUE;
        int dist1, dist2;
        int[][] dist = new int[2][numN + 1];

        dijkstra(via1, dist[0]);
        dijkstra(via2, dist[1]);

        // 75%에서 틀림 -> 불가능한 경로 처리
        if (dist[0][1] == MAX || dist[0][via2] == MAX || dist[1][numN] == MAX) {
            dist1 = MAX;
        } else {
            dist1 = dist[0][1] + dist[0][via2] + dist[1][numN];
        }

        if (dist[1][1] == MAX || dist[1][via1] == MAX || dist[0][numN] == MAX) {
            dist2 = MAX;
        } else {
            dist2 = dist[1][1] + dist[1][via1] + dist[0][numN];
        }

        res = Math.min(dist1, dist2);

        if (res == MAX) {
            res = -1;
        }
    }

    static void dijkstra(int start, int[] list) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node now, next;
        int nextDist;

        Arrays.fill(list, Integer.MAX_VALUE);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            // now : start 노드부터 경로를 계산할 노드
            now = pq.remove();

            // 최적의 경로가 계산된 경우 다음 노드 탐색
            if (list[now.dest] < now.dist) {
                continue;
            }

            list[now.dest] = now.dist;
            for (int i = 0; i < route.get(now.dest).size(); i++) {
                // next : now 노드와 연결된 노드
                next = route.get(now.dest).get(i);

                // 거리 갱신이 필요한 노드만 pq에 추가
                nextDist = next.dist + now.dist;
                if (list[next.dest] > nextDist) {
                    pq.add(new Node(next.dest, nextDist));
                }
            }
        }
    }
}
