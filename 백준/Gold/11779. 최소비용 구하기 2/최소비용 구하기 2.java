import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int node, edge;
    static int start, end;
    static List<List<int[]>> map;

    static class Route {
        int cost;
        ArrayList<Integer> route;

        Route(int num) {
            this.cost = Integer.MAX_VALUE;
            this.route = new ArrayList<>();
            route.add(num);
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        node = Integer.parseInt(br.readLine().trim());
        edge = Integer.parseInt(br.readLine().trim());

        map = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(from).add(new int[] { to, cost });
        }

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void getRes() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        Route[] route = new Route[node + 1];

        for (int i = 1; i <= node; i++) {
            route[i] = new Route(i);
        }

        pq.offer(new int[] { start, 0 });
        route[start].cost = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowN = now[0];
            int nowC = now[1];

            if (nowN == end) {
                break;
            }

            if (route[nowN].cost < nowC) {
                continue;
            }

            for (int[] next : map.get(nowN)) {
                int nextN = next[0];
                int nextC = nowC + next[1];

                if (route[nextN].cost > nextC) {
                    route[nextN].route = new ArrayList<>();

                    for (int i : route[nowN].route) {
                        route[nextN].route.add(i);
                    }

                    route[nextN].cost = nextC;
                    route[nextN].route.add(nextN);

                    pq.offer(new int[] { nextN, nextC });
                }
            }
        }

        sb.append(route[end].cost).append("\n").append(route[end].route.size()).append("\n");
        for (int i : route[end].route) {
            sb.append(i).append(" ");
        }
    }
}
