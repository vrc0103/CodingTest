import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int num, cnt;
    static int[] order;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static ArrayDeque<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());

        order = new int[num + 1];
        for(int i = 0; i <= num; i++) {
            list.add(new ArrayList<>());
        }

        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int student1 = Integer.parseInt(st.nextToken());
            int student2 = Integer.parseInt(st.nextToken());

            list.get(student1).add(student2);
            order[student2]++;

            // 아래처럼 구현하면 new ArrayList를 할당해서 저장하고 반환하긴 하는데 해시맵에 들어가지는 않음
            // hmap.getOrDefault(student1, new ArrayList<>()).add(student2);
        }
    }

    static void getRes() {
        for(int i = 1; i <= num; i++) {
            // 시작 정점 추가
            if(order[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();

            sb.append(now).append(" ");

            // 연결된 노드의 진입차수 1 감소
            for(int tmp : list.get(now)) {
                order[tmp]--;

                // 진입차수가 0인 노드를 큐에 추가
                if(order[tmp] == 0) {
                    q.offer(tmp);
                }
            }
        }
    }
}

