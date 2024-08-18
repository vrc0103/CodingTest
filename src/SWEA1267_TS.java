import java.io.*;
import java.util.*;

public class SWEA1267_TS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase = 10;
    static int numOfVtx, numOfEdge;
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> linked;

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");

            getLink();

            getTopologicalSort();
        }

        System.out.print(sb);
    }

    static void getLink() throws IOException {
        int from, to;

        st = new StringTokenizer(br.readLine());
        numOfVtx = Integer.parseInt(st.nextToken()) + 1;
        numOfEdge = Integer.parseInt(st.nextToken());

        indegree = new int[numOfVtx];

        // 2차원 ArrayList 생성
        linked = new ArrayList<>();
        for (int i = 0; i < numOfVtx; i++) {
            linked.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int e = 0; e < numOfEdge; e++) {
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            // 연결된 간선 표시 및 진입 차수 증가
            linked.get(from).add(to);
            indegree[to]++;
        }
    }

    // BFS 방식이 직관적이므로 BFS로 구현
    // 1. 진입 차수가 0인 정점을 Queue에 추가
    // 2. 해당 정점에서 나가는 간선 제거 = 진입 차수 갱신
    // 3. Queue가 빌 때까지 반복
    static void getTopologicalSort() {
        int now;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> edge;

        // 1. 진입 차수가 0인 정점 추가
        for (int i = 1; i < numOfVtx; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            now = queue.poll();
            sb.append(now).append(" ");

            // 2. 간선 제거 = 진입 차수 갱신
            edge = linked.get(now);

            for (int e : edge) {
                indegree[e]--;

                // 1. 진입 차수가 0인 정점 추가
                if (indegree[e] == 0) {
                    queue.add(e);
                }
            }
        }

        sb.append("\n");
    }
}
