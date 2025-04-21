import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int size;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        size = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList<>();
        for(int i = 0; i <= size; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < size - 1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            graph.get(num1).add(num2);
            graph.get(num2).add(num1);
        }
    }

    static void getRes() {
        int[] parents = new int[size + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(1);
        while(!q.isEmpty()) {
            int now = q.poll();

            for(int i : graph.get(now)) {
                if(parents[i] == 0) {
                    parents[i] = now;
                    q.add(i);
                }
            }
        }

        for(int i = 2; i <= size; i++) {
            sb.append(parents[i]).append("\n");
        }
    }
}
