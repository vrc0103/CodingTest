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
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] list;

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
        long cost;

        num = Integer.parseInt(br.readLine());
        islandR = new int[num];
        islandC = new int[num];
        list = new ArrayList[num];

        st = new StringTokenizer(br.readLine());
        for (int r = 0; r < num; r++) {
            islandR[r] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < num; c++) {
            islandC[c] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < num; i++) {
            list[i] = new ArrayList<>();

            // 각 노드의 간선 비용을 모두 확인해야 하므로 j도 0부터 시작
            for (int j = 0; j < num; j++) {
                if (i == j) {
                    continue;
                }

                cost = (long) (Math.pow(islandR[i] - islandR[j], 2) + Math.pow(islandC[i] - islandC[j], 2));

                // 각 노드별 코스트를 모두 저장
                list[i].add(new Node(j, cost));
            }
        }

        tax = Double.parseDouble(br.readLine());
    }

    static void getPrim() {
        int count = 0;
        Node now, next;

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

            for (int i = 0; i < list[now.node].size(); i++) {
                next = list[now.node].get(i);

                // 현재 노드와 연결된 노드 중 방문하지 않은 노드를 pq에 추가
                if (!visited[next.node]) {
                    pq.offer(next);
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
