import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final long INF = Long.MAX_VALUE;

    static int numN, numE, numT;
    static int[] locT;
    static ArrayList<ArrayList<int[]>> map = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 정보
        st = new StringTokenizer(br.readLine().trim());
        numN = Integer.parseInt(st.nextToken());
        numE = Integer.parseInt(st.nextToken());
        numT = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= numN; i++) {
            map.add(new ArrayList<>());
        }

        for(int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 역방향 그래프
            map.get(to).add(new int[] {from, cost});
        }

        locT = new int[numT];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < numT; i++) {
            locT[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(locT);

        // 풀이
        long[] costs = new long[numN + 1];
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));

        Arrays.fill(costs, INF);

        // 면접장이 있는 도시는 비용 0으로 초기화 및 다익스트라 출발점으로 추가
        for (int i = 0; i < numT; i++) {
            int node = locT[i];
            costs[node] = 0;
            pq.add(new long[] {node, 0});
        }

        // 다익스트라
        while (!pq.isEmpty()) {
            long[] now = pq.remove();
            int nowNode = (int) now[0];
            long nowCost = now[1];
            
            if (costs[nowNode] < nowCost){
                continue;
            }
            
            for (int[] next : map.get(nowNode)) {
                int nextNode = next[0];
                long nextCost = nowCost + next[1];

                if (nextCost < costs[nextNode]) {
                    costs[nextNode] = nextCost;
                    pq.add(new long[] {nextNode, nextCost});
                }
            }
        }

        int resNode = 0;
        long resCost = 0;

        // 면접장이 아닌 도시 중에서, 가장 멀리 떨어진 도시(최대 거리) 찾기
        for (int i = 1; i <= numN; i++) {
            // i가 면접장이 아니고, 가장 가까운 면접장이 더 멀면 갱신
            if (Arrays.binarySearch(locT, i) < 0 && costs[i] != INF && costs[i] > resCost) {
                resCost = costs[i];
                resNode = i;
            }
        }
        
        System.out.printf("%d\n%d\n", resNode, resCost);
    }
}
