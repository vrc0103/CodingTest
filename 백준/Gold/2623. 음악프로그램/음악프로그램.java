import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int sing, pd;
    static int[] inDegree;
    static boolean[] selected;

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static ArrayDeque<Integer> q = new ArrayDeque<>();


    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        sing = Integer.parseInt(st.nextToken());
        pd = Integer.parseInt(st.nextToken());

        inDegree = new int[sing + 1];
        selected = new boolean[sing + 1];

        for(int i = 0; i <= sing; i++) {
            map.add(new ArrayList<>());
        }

        while(pd-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int cnt = Integer.parseInt(st.nextToken());
            int sing1 = Integer.parseInt(st.nextToken());

            cnt--;

            while(cnt-- > 0) {
                int sing2 = Integer.parseInt(st.nextToken());
                map.get(sing1).add(sing2);
                inDegree[sing2]++;
                sing1 = sing2;
            }
        }

        for(int i = 1; i <= sing; i++) {
            if(inDegree[i] == 0) {
                q.offer(i);
                selected[i] = true;
            }
        }
    }

    static void getRes() {
        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append("\n");
            
            for(int n : map.get(now)) {
                inDegree[n]--;

                if(inDegree[n] == 0) {
                    q.offer(n);
                    selected[n] = true;
                }
            }
        }

        // 선택 안된 가수 존재 = 사이클 발생
        for(int i = 1; i <= sing; i++) {
            if(!selected[i]) {
                sb = new StringBuilder();
                sb.append("0\n");

                break;
            }
        }
    }
}
