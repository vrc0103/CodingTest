import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int num, count;
    static int res;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        cycleGame();

        System.out.println(res);
    }

    static void cycleGame() throws IOException {
        int node1, node2;

        st = new StringTokenizer(br.readLine().trim());

        num = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
        }

        for (int cnt = 0; cnt < count; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            if (isCycle(node1, node2)) {
                res = cnt + 1;
                return;
            }
        }
    }

    static boolean isCycle(int node1, int node2) {
        int parent1 = getParent(node1);
        int parent2 = getParent(node2);

        // 두 노드가 이미 동일한 부분집합에 포함됨
        // -> 이번 연결에서 사이클 형성
        if (parent1 == parent2) {
            return true;
        }

        // 서로 다른 부분집합인 경우 Union 수행
        else {
            if (parent1 < parent2) {
                parent[parent2] = parent1;
            } else {
                parent[parent1] = parent2;
            }

            return false;
        }
    }

    static int getParent(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = getParent(parent[node]);
    }
}
