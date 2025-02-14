import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int numN, numE;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 정보
        int from, to, cost;

        numN = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i <= numN; i++) {
            graph.add(new ArrayList<>());
        }

        numE = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            if (from == to) {
                continue;
            }

            graph.get(from).add(new int[] { to, cost });
            graph.get(to).add(new int[] { from, cost });
        }

        // 풀이
        int[] now;
        boolean[] linked = new boolean[numN + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        ArrayList<int[]> MST = new ArrayList<>();

        // Prim
        pq.add(new int[] { 1, 0 });

        while (!pq.isEmpty()) {
            now = pq.remove();

            if (linked[now[0]]) {
                continue;
            }

            linked[now[0]] = true;
            MST.add(now);
            res += now[1];

            // 새로 추가된 노드와 연결된 노드만 추가
            for (int[] node : graph.get(now[0])) {
                // 사이클 처리 : 아직 방문하지 않은 노드만 탐색
                if (!linked[node[0]]) {
                    pq.add(node);
                }
            }
        }

        System.out.println(res);
    }
}
