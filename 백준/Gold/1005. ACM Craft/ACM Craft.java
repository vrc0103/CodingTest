import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int node, edge;
    static int target;
    static int[] time;
    static int[] prev;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            setInfo();

            getRes();
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        prev = new int[node + 1];
        time = new int[node + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= node; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            prev[to]++;
        }

        target = Integer.parseInt(br.readLine().trim());
    }

    static void getRes() {
        int[] dp = new int[node + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        // 시작점
        for (int i = 1; i <= node; i++) {
            if (prev[i] == 0) {
                dp[i] = time[i];
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph.get(now)) {
                // 더 큰 값으로 갱신 : 가장 나중에 완성된 건물이 기준이기 때문
                if (dp[next] < dp[now] + time[next]) {
                    dp[next] = dp[now] + time[next];
                }

                // 위상정렬과 동일하게 이전 작업이 모두 완료되면 queue에 추가
                prev[next]--;

                if (prev[next] == 0) {
                    q.add(next);
                }
            }
        }

        sb.append(dp[target]).append('\n');
    }
}
