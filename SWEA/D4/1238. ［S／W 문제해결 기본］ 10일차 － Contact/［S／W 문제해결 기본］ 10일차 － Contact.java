import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int size, start;
    static boolean[] selected;
    static ArrayDeque<Integer> q;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = 10;

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = 0;
        selected = new boolean[101];
        q = new ArrayDeque<>();
        graph = new ArrayList<>();
        for(int i = 0; i <= 100; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken()) / 2;
        start = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        while(size-- > 0) {
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
        }
    }

    static void getRes() {
        int last = 0;

        q.offer(start);
        selected[start] = true;
        while(!q.isEmpty()) {
            int len = q.size();
            last = 0;

            while(len-- > 0) {
                int now = q.poll();
                last = Math.max(last, now);

                for(int i : graph.get(now)) {
                    if(!selected[i]) {
                        q.offer(i);
                        selected[i] = true;
                    }
                }
            }

        }
        
        res = last;
    }
}
