import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int ladder, snake;
    static int[] map = new int[101];
    static int[] cost = new int[101];

    static class Info implements Comparable<Info>{
        int loc;
        int cost;

        public Info(int loc, int cost) {
            this.loc = loc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        // 초기값
        int from, to;

        st = new StringTokenizer(br.readLine().trim());
        ladder = Integer.parseInt(st.nextToken());
        snake = Integer.parseInt(st.nextToken());

        for(int i = 0; i < ladder + snake; i++) {
            st = new StringTokenizer(br.readLine().trim());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            map[from] = to;
        }

        Arrays.fill(cost, Integer.MAX_VALUE);

        // 풀이
        Info now;
        int nextLoc, nextCost;
        PriorityQueue<Info> pq = new PriorityQueue<>();

        pq.add(new Info(1, 0));

        while(!pq.isEmpty()) {
            now = pq.remove();

            if(now.loc == 100) {
                res = now.cost;

                break;
            }

            for(int i = 1; i <= 6; i++) {
                nextLoc = now.loc + i;
                nextCost = now.cost + 1;

                if(nextLoc > 100) {
                    continue;
                }

                // 사다리, 뱀
                if(map[nextLoc] > 0) {
                    nextLoc = map[nextLoc];
                }

                if(cost[nextLoc] > nextCost) {
                    cost[nextLoc] = nextCost;
                    pq.add(new Info (nextLoc, nextCost));
                }
            }
        }

        System.out.println(res);
    }
}