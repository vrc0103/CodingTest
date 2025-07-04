import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int node;
    static int start;
    static List<List<int[]>> map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        node = Integer.parseInt(br.readLine().trim());

        map = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < node; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int now = Integer.parseInt(st.nextToken());

            while (true) {
                int next = Integer.parseInt(st.nextToken());

                if (next == -1) {
                    break;
                }

                int cost = Integer.parseInt(st.nextToken());

                map.get(now).add(new int[] { next, cost });
            }

            if (map.get(now).size() == 1) {
                start = now;
            }
        }
    }

    static void getRes() {
        // 리프 노드부터 시작해서 가장 먼 리프 노드까지 거리 계산
        for (int i = 0; i < 2; i++) {
            boolean[] visited = new boolean[node + 1];
            ArrayDeque<int[]> bfs = new ArrayDeque<>();
            bfs.offer(new int[] { start, 0 });
            visited[start] = true;

            int max = 0;
            int far = start;

            while (!bfs.isEmpty()) {
                int[] now = bfs.poll();

                if (now[1] > max) {
                    max = now[1];
                    far = now[0];
                }

                for (int[] next : map.get(now[0])) {
                    if (!visited[next[0]]) {
                        visited[next[0]] = true;
                        bfs.offer(new int[] { next[0], now[1] + next[1] });
                    }
                }
            }

            start = far; // 첫 번째 BFS로는 지름의 끝점 찾기
            res = max; // 두 번째 BFS의 결과가 트리 지름
        }
    }
}
