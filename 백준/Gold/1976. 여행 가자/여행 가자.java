import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int num;
    static int plan;
    static int[] route;
    static int[][] map;
    static boolean[][] visit;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInfo();

        getVisit();

        getRes();
    }

    static void getInfo() throws IOException {
        num = Integer.parseInt(br.readLine());
        plan = Integer.parseInt(br.readLine());

        map = new int[num][num];
        route = new int[plan];

        // 도시 지도
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < num; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방문 계획
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < plan; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getVisit() {
        int city;

        visit = new boolean[num][num];

        for (int i = 0; i < num; i++) {
            visit[i][i] = true;

            // 해당 도시에서 바로 갈 수 있는 도시 파악
            for (int j = 0; j < num; j++) {
                if (map[i][j] == 1) {
                    visit[i][j] = true;
                    queue.offer(j);
                }
            }

            // 다른 도시를 경유해서 갈 수 있는 도시 파악
            while (!queue.isEmpty()) {
                city = queue.poll();
                for (int j = 0; j < num; j++) {
                    if (map[city][j] == 1 && !visit[i][j]) {
                        visit[i][j] = true;
                        queue.offer(j);
                    }
                }
            }
        }
    }

    static void getRes() {
        int now, next;
        String res = "YES";

        for (int i = 0; i < plan - 1; i++) {
            // 도시 번호가 1 ~ N 이므로 -1
            now = route[i] - 1;
            next = route[i + 1] - 1;

            if (!visit[now][next]) {
                res = "NO";
                break;
            }
        }

        System.out.println(res);
    }
}
