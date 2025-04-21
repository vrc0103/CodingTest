import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int num, dep, start;
    static int[] cost;
    static List<List<int[]>> graph;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 0; tc < testCase; tc++) {
            setInfo();

            getRes();
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        dep = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        cost = new int[num + 1];
        graph = new ArrayList<>();
        for(int i = 0; i <= num; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < dep; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int[] {to, cost});
        }
    }

    static void getRes() {
        int INF = 987_654_321;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        Arrays.fill(cost, INF);

        pq.offer(new int[] {start, 0});
        while(!pq.isEmpty()) {
            int[] now = pq.poll();

            if(cost[now[0]] <= now[1]) {
                continue;
            }

            cost[now[0]] = now[1];

            for(int[] next : graph.get(now[0])) {
                int nNode = next[0];
                int nCost = now[1] + next[1];

                if(cost[nNode] > nCost) {
                    pq.add(new int[] {nNode, nCost});
                }
            }
        }

        int cnt = 0;
        int max = 0;
        for(int i = 1; i <= num; i++) {
            if(cost[i] < INF) {
                cnt++;
                max = Math.max(max, cost[i]);
            }
        }

        sb.append(cnt).append(" ").append(max).append("\n");
    }
}
