import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numN, numE;
    static int[] need;
    static List<List<Integer>> map;
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        numN = Integer.parseInt(st.nextToken());
        numE = Integer.parseInt(st.nextToken());

        need = new int[numN + 1];
        map = new ArrayList<>();
        for(int i = 0; i <= numN; i++) {
            map.add(new ArrayList<>());
        }

        for(int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            need[next]++;
            map.get(prev).add(next);
        }
    }

    static void getRes() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i <= numN; i++) {
            if(need[i] == 0) {
                pq.offer(i);
            }
        }

        while(!pq.isEmpty()) {
            int now = pq.poll();

            sb.append(now).append(" ");

            for(int next : map.get(now)){
                need[next]--;

                if(need[next] == 0) {
                    pq.offer(next);
                }
            }
        }
    }
}
