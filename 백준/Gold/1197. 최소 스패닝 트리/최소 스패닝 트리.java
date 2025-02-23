import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int numN, numE;
    static int[] root;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

    public static void main(String[] args) throws Exception {
        int from, to, cost;

        st = new StringTokenizer(br.readLine().trim());
        numN = Integer.parseInt(st.nextToken());
        numE = Integer.parseInt(st.nextToken());

        root = new int[numN + 1];
        for(int i = 0; i <= numN; i++) {
            root[i] = i;
        }

        for(int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            pq.add(new int[] {from, to, cost});
        }

        // 풀이
        int[] now;

        while(!pq.isEmpty()) {
            now = pq.remove();

            if(union(now[0], now[1])) {
                res += now[2];
            }
        }

        System.out.println(res);
    }

    static boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if(p1 == p2) {
            return false;
        } else if(p1 < p2) {
            root[p2] = p1;
        } else {
            root[p1] = p2;
        }

        return true;
    }

    static int find(int n) {
        if(root[n] == n) {
            return n;
        }

        return root[n] = find(root[n]);
    }
}
