import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int numN, numE;
    static int[] root;
    static PriorityQueue<int[]> graph = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

    public static void main(String[] args) throws Exception {
        // 정보
        int from, to, cost;

        numN = Integer.parseInt(br.readLine().trim());
        numE = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            if (from == to) {
                continue;
            }

            graph.add(new int[] { from, to, cost });
        }

        // 풀이
        int[] now;

        root = new int[numN + 1];
        for (int i = 0; i <= numN; i++) {
            root[i] = i;
        }

        while (!graph.isEmpty()) {
            // 비용이 작은 간선부터 선택
            now = graph.remove();

            if (union(now[0], now[1])) {
                res += now[2];
            }
        }

        System.out.println(res);
    }

    static boolean union(int n1, int n2) {
        int p1 = getRoot(n1);
        int p2 = getRoot(n2);

        // 이미 연결되어 있는 경우 사이클 방지를 위해 연결 X
        if (p1 == p2) {
            return false;
        } else if (p1 < p2) {
            root[p2] = p1;
        } else {
            root[p1] = p2;
        }

        return true;
    }

    static int getRoot(int n) {
        if (root[n] == n) {
            return n;
        }

        return root[n] = getRoot(root[n]);
    }
}
