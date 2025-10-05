import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int node;
    static int root;
    static int num;
    static int[] query;
    static List<List<Integer>> tree;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < node - 1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree.get(from).add(to);
            tree.get(to).add(from);
        }
    }

    static void getRes() throws Exception {
        query = new int[node + 1];
        boolean[] visited = new boolean[node + 1];

        dfs(root, visited);

        for (int i = 0; i < num; i++) {
            int num = Integer.parseInt(br.readLine().trim());

            sb.append(query[num]).append("\n");
        }
    }

    static int dfs(int sub, boolean[] visited) {
        int cnt = 0;
        visited[sub] = true;

        for (int i : tree.get(sub)) {
            if (!visited[i]) {
                cnt += dfs(i, visited);
            }
        }

        return query[sub] = cnt + 1;
    }
}
