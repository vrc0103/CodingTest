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
        int res = 0;
        List<int[]> build = new ArrayList<>();

        for (int i = 1; i <= node; i++) {
            if (prev[i] == 0) {
                build.add(new int[] { i, time[i] });
            }
        }

        while (true) {
            Collections.sort(build, (o1, o2) -> o1[1] - o2[1]);

            int[] now = build.remove(0);
            List<Integer> done = new ArrayList<>();

            done.add(now[0]);
            res += now[1];

            for (int i = 0; i < build.size(); i++) {
                build.get(i)[1] -= now[1];

                if (build.get(i)[1] == 0) {
                    done.add(build.remove(i)[0]);
                    i--;
                }
            }

            for (int d : done) {
                if (d == target) {
                    sb.append(res).append("\n");
                    return;
                }

                for (int next : graph.get(d)) {
                    prev[next]--;

                    if (prev[next] == 0) {
                        build.add(new int[] { next, time[next] });
                    }
                }
            }
        }
    }
}
