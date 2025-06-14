import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int node, edge;
    static int start, end;
    static List<List<int[]>> map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(n1).add(new int[] { n2, cost });
            map.get(n2).add(new int[] { n1, cost });
        }
    }

    static void getRes() {
        int[] weight = new int[node + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        weight[start] = Integer.MAX_VALUE;
        pq.offer(new int[] { start, Integer.MAX_VALUE });
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (now[0] == end) {
                res = now[1];
                return;
            }

            for (int[] next : map.get(now[0])) {
                int minWeight = Math.min(now[1], next[1]);

                if (weight[next[0]] < minWeight) {
                    weight[next[0]] = minWeight;
                    pq.offer(new int[] { next[0], minWeight });
                }
            }
        }
    }
}
