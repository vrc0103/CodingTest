import java.io.*;
import java.util.*;

public class SWEA1251_Kruscal {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int num;
    static long res;
    static double tax;
    static int[] parents;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {
        int from, to;
        long cost;

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        // 정렬 시 cost 기준으로
        @Override
        public int compareTo(Edge edge) {
            // 127 ~ -128 범위를 벗어나는 숫자는 서로 다른 객체로 취급되므로 compare 필요
            return Long.compare(this.cost, edge.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            getIslands();

            getKruscal();

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb.toString());
    }

    static void getIslands() throws IOException {
        int[] islandR;
        int[] islandC;
        long cost;

        num = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        islandR = new int[num];
        islandC = new int[num];

        parents = new int[num];
        for (int i = 0; i < num; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int r = 0; r < num; r++) {
            islandR[r] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < num; c++) {
            islandC[c] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < num; i++) {
            // 양 끝 node에 따라 간선의 cost가 변하지 않으므로 j는 i+1부터
            for (int j = i + 1; j < num; j++) {
                cost = (long) (Math.pow(islandR[i] - islandR[j], 2) + Math.pow(islandC[i] - islandC[j], 2));
                pq.offer(new Edge(i, j, cost));
            }
        }

        tax = Double.parseDouble(br.readLine());
    }

    static void getKruscal() {
        int count = 1;
        Edge edge;

        res = 0;
        while (count < num) {
            // PQ -> 비용이 적은 간선부터 가져옴
            edge = pq.poll();

            // 이미 union된 경우
            if (getParents(edge.from) == getParents(edge.to)) {
                continue;
            }

            // 간선을 선택하고 cost 계산
            getUnion(edge.from, edge.to);
            res += edge.cost;
            count++;
        }
        res = Math.round(res * tax);
    }

    static void getUnion(int city1, int city2) {
        int parent1 = getParents(city1);
        int parent2 = getParents(city2);

        if (parent2 < parent1) {
            parents[parent1] = parent2;
        } else {
            parents[parent2] = parent1;
        }
    }

    static int getParents(int city) {
        if (parents[city] == city) {
            return city;
        }

        return parents[city] = getParents(parents[city]);
    }
}
