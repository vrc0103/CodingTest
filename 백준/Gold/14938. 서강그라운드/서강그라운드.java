import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int node, edge, limit;
    static int[] cnt;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        node = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        cnt = new int[node + 1];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= node; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        dist = new int[node + 1][node + 1];
        for (int i = 1; i <= node; i++) {
            Arrays.fill(dist[i], 987654321);
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[from][to] = cost;
            dist[to][from] = cost;
        }
    }

    static void getRes() {
        // 플루이드 - 워셜
        for (int k = 1; k <= node; k++) {
            for (int i = 1; i <= node; i++) {
                for (int j = 1; j <= node; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for (int i = 1; i <= node; i++) {
            int sum = 0;

            for (int j = 1; j <= node; j++) {
                if (dist[i][j] <= limit) {
                    sum += cnt[j];
                }
            }

            res = Math.max(res, sum);
        }
    }
}
