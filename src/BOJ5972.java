import java.io.*;
import java.util.*;

public class BOJ5972 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int numNode, numRoad;
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();

    static class Node implements Comparable<Node> {
        int loc, cost;

        public Node(int loc, int cost) {
            this.loc = loc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        int node1, node2;
        int cost;

        st = new StringTokenizer(br.readLine().trim());
        numNode = Integer.parseInt(st.nextToken());
        numRoad = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= numNode; i++) {
            map.add(new ArrayList<Node>());
        }

        // 경로 및 비용 저장
        for (int i = 0; i < numRoad; i++) {
            st = new StringTokenizer(br.readLine().trim());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            map.get(node1).add(new Node(node2, cost));
            map.get(node2).add(new Node(node1, cost));
        }
    }

    // Dijkstra
    static void getRes() {
        int[] dist = new int[numNode + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 가중치 최대로 초기화 및 시작 지점 0으로 변경
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.add(new Node(1, dist[1]));

        Node nowNode;
        int nowLoc, nowCost;
        int nextLoc, nextCost;

        while (!pq.isEmpty()) {
            nowNode = pq.remove();
            nowLoc = nowNode.loc;
            nowCost = nowNode.cost;

            // 저장된 값이 현재 값보다 작음 = 이미 최적화 된 상태
            if (dist[nowLoc] < nowCost) {
                continue;
            }

            // 현재 노드에서 연결된 노드 탐색
            for (int i = 0; i < map.get(nowLoc).size(); i++) {
                nextLoc = map.get(nowLoc).get(i).loc;
                nextCost = nowCost + map.get(nowLoc).get(i).cost; // 현재 노드까지 거리 + 현재 노드에서 다음 노드까지 거리

                // 저장된 값보다 현재 값이 작으면 갱신, pq에 추가
                if (nextCost < dist[nextLoc]) {
                    dist[nextLoc] = nextCost;
                    pq.add(new Node(nextLoc, nextCost));
                }
            }
        }

        res = dist[numNode];
    }
}
