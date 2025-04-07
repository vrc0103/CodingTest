import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int numN, numE;
    static int start, end;
    static List<List<int[]>> map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        numN = Integer.parseInt(br.readLine().trim());
        numE = Integer.parseInt(br.readLine().trim());

        map = new ArrayList<>();
        for(int i = 0; i <= numN; i++) {
            map.add(new ArrayList<int[]>());
        }

        while(numE-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(from).add(new int[] {to, cost});
        }

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void getRes() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] value = new int[numN + 1];
        Arrays.fill(value, Integer.MAX_VALUE);

        pq.offer(new int[] {start, 0});
        value[start] = 0;

        while(!pq.isEmpty()) {
            int[] now = pq.poll();

            if(value[now[0]] < now[1]) {
                continue;
            }

            for(int[] next : map.get(now[0])) {
                if(value[next[0]] > next[1] + value[now[0]]) {
                    value[next[0]] = next[1] + value[now[0]];
                    pq.add(new int[] {next[0], value[next[0]]});
                }
            }
        }

        res = value[end];
    }
}
