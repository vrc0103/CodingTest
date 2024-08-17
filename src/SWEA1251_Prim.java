import java.io.*;
import java.util.*;

public class SWEA1251_Prim {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int num;
    static long res;
    static double tax;
    static boolean[] visited;
    static long[][] cost;
    static PriorityQueue<Node> pq;

    static class Node implements Comparable<Node> {
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.cost, node.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            getIslands();

            getPrim();

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb.toString());
    }

    static void getIslands() throws IOException {
        int[] islandR;
        int[] islandC;
        long val;

        num = Integer.parseInt(br.readLine());
        islandR = new int[num];
        islandC = new int[num];
        cost = new long[num][num];

        st = new StringTokenizer(br.readLine());
        for (int r = 0; r < num; r++) {
            islandR[r] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < num; c++) {
            islandC[c] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < num; i++) {
            // 각 노드의 간선 비용을 모두 확인해야 하므로 j도 0부터 시작
            for (int j = 0; j < num; j++) {
                if (i == j) {
                    continue;
                }

                val = (long) (Math.pow(islandR[i] - islandR[j], 2) + Math.pow(islandC[i] - islandC[j], 2));

                // 각 노드별 코스트를 모두 저장
                cost[i][j] = val;
            }
        }

        tax = Double.parseDouble(br.readLine());
    }

    static void getPrim() {
        int count = 0;
        Node now;

        pq = new PriorityQueue<>();
        visited = new boolean[num];

        res = 0;
        pq.offer(new Node(0, 0));
        while (count < num) {
            // cost가 적은 순서대로 불려옴
            now = pq.poll();

            // 연결된 노드는 생략
            if (visited[now.node]) {
                continue;
            }

            for (int i = 0; i < num; i++) {
                // 현재 노드와 연결된 노드 중 방문하지 않은 노드를 pq에 추가
                if (i != now.node && !visited[i]) {
                    pq.offer(new Node(i, cost[now.node][i]));
                }
            }

            // 노드를 연결하고 비용 계산
            visited[now.node] = true;
            res += now.cost;
            count++;
        }

        res = Math.round(res * tax);
    }
}
