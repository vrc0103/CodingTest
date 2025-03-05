import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int numN, numE;
    static int[] root;
    static boolean[] selected;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        numN = Integer.parseInt(st.nextToken());
        numE = Integer.parseInt(st.nextToken());

        selected = new boolean[numN + 1];
        root = new int[numN + 1];

        for(int i = 1; i <= numN; i++) {
            root[i] = i;
        }

        for(int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new int[] {from, to, cost});
        }
    }

    static void getRes() {
        int linked = 1;

        while(!pq.isEmpty()) {
            int[] now = pq.remove();

            // 마을을 2개로 나눠야 함 -> 가장 비용 높은 경로 빼고 MST 형성
            if(union(now) && linked < numN - 1) {
                linked++;
                res += now[2];
            }
        }
    } 

    static boolean union(int[] edge) {
        int p1 = find(edge[0]);
        int p2 = find(edge[1]);

        if(p1 > p2) {
            root[p1] = p2;
        } else if(p1 < p2) {
            root[p2] = p1;
        } else {
            return false;
        }

        return true;
    }

    static int find(int node) {
        if(root[node] == node) {
            return node;
        }

        return root[node] = find(root[node]);
    }
}
