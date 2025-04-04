import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num, cnt;
    static int[][] graph;

    static int INF = 1_000_000_000;


    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        res = 0;
        
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());

        graph = new int[num + 1][num + 1];

        // 큰 값으로 초기화
        for(int i = 0; i < num + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        // 연결된 노드 저장
        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = 1;
        }
    }

    static void getRes() {
        // 플루이드 워셜
        for(int k = 1; k <= num; k++) {
            for(int i = 1; i <= num; i++) {
                for(int j = 1; j <= num; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for(int i = 1; i <= num; i++) {

            for(int j = 1; j <= num; j++) {
                if(graph[i][j] == INF && graph[j][i] == INF) {
                    break;
                }

                if(j == num) {
                    res++;
                }
            }
        }
    }
}
