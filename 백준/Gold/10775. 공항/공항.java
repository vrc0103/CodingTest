import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int port, cnt;
    static int[] plane;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        port = Integer.parseInt(br.readLine().trim());
        cnt = Integer.parseInt(br.readLine().trim());
        plane = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            plane[i] = Integer.parseInt(br.readLine().trim());
        }
    }

    static void getRes() {
        int MAX = 100_001;
        parent = new int[MAX];

        for (int i = 0; i < MAX; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < cnt; i++) {
            int now = plane[i];
            int next = find(now);

            if (next == 0) {
                break;
            }

            res++;
            union(next, next - 1);
        }
    }

    static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }

    static int find(int n) {
        if (parent[n] == n) {
            return n;
        }

        return parent[n] = find(parent[n]);
    }
}
