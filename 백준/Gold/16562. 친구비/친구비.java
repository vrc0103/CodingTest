import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int node, edge, cost;
    static int[] money;
    static int[] root;
    static List<List<Integer>> map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        cost = Integer.parseInt(st.nextToken());

        money = new int[node + 1];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= node; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map.get(from).add(to);
            map.get(to).add(from);
        }
    }

    static void getRes() {
        root = new int[node + 1];

        for (int i = 1; i <= node; i++) {
            root[i] = i;
        }

        for (int i = 1; i <= node; i++) {
            for (int n : map.get(i)) {
                union(i, n);
            }
        }

        for (int i = 1; i <= node; i++) {
            if (find(i) == i) {
                res += money[i];
            }
        }

        sb.append(cost >= res ? res : "Oh no").append("\n");
    }

    static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 == p2) {
            return;
        } else if (money[p1] > money[p2]) {
            root[p1] = p2;
        } else {
            root[p2] = p1;
        }
    }

    static int find(int n) {
        if (root[n] == n) {
            return n;
        }

        return root[n] = find(root[n]);
    }
}
