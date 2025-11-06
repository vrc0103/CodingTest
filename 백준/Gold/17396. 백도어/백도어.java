import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());

        boolean[] pos = new boolean[node];
        List<List<long[]>> map = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < node; i++) {
            map.add(new ArrayList<>());
            pos[i] = Integer.parseInt(st.nextToken()) == 1 ? false : true;
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(from).add(new long[] { to, cost });
            map.get(to).add(new long[] { from, cost });
        }

        long INF = 30_000_000_000l;
        long[] dist = new long[node];
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));

        Arrays.fill(dist, INF);
        pq.offer(new long[] { 0, 0 });
        dist[0] = 0;

        while (!pq.isEmpty()) {
            long[] now = pq.poll();
            int nowN = (int) now[0];
            long nowD = now[1];

            if (dist[nowN] < nowD) {
                continue;
            }

            if (nowN == node - 1) {
                break;
            }

            for (long[] next : map.get(nowN)) {
                int nextN = (int) next[0];
                long nextD = nowD + next[1];

                if (nextN != node - 1 && !pos[nextN]) {
                    continue;
                }

                if (dist[nextN] > nextD) {
                    dist[nextN] = nextD;
                    pq.offer(new long[] { nextN, nextD });
                }
            }
        }

        long res = dist[node - 1];
        if (res == INF) {
            res = -1;
        }

        System.out.println(res);
    }
}
