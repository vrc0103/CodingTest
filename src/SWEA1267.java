import java.io.*;
import java.util.*;

public class SWEA1267 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase = 10;
    static int numOfVtx, numOfEdge;
    static boolean[] done;
    static boolean[][] linked;

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");

            getLink();

            getSeq();

            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void getLink() throws IOException {
        int from, to;

        st = new StringTokenizer(br.readLine());
        numOfVtx = Integer.parseInt(st.nextToken()) + 1;
        numOfEdge = Integer.parseInt(st.nextToken());

        linked = new boolean[numOfVtx][numOfVtx];
        done = new boolean[numOfVtx];

        st = new StringTokenizer(br.readLine());
        for (int e = 0; e < numOfEdge; e++) {
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            // 선행 관계 입력
            linked[from][to] = true;
        }
    }

    static void getSeq() {
        int now;
        boolean posible;
        Queue<Integer> queue = new LinkedList<>();

        // i번째 task의 선행 작업 X -> 시작 가능
        for (int c = 1; c < numOfVtx; c++) {
            posible = true;

            for (int r = 1; r < numOfVtx; r++) {
                if (linked[r][c]) {
                    posible = false;
                }
            }

            if (posible) {
                queue.add(c);
            }
        }

        while (!queue.isEmpty()) {
            posible = true;
            now = queue.poll();

            if (done[now]) {
                continue;
            }

            // 선행 작업이 모두 완료되었는지 확인
            for (int i = 1; i < numOfVtx; i++) {
                // 선행 작업이 있는데 완료 X
                if (linked[i][now] && !done[i]) {
                    posible = false;
                    break;
                }
            }

            if (posible) {
                done[now] = true;
                sb.append(now).append(" ");

                for (int next = 1; next < numOfVtx; next++) {
                    if (linked[now][next] && !done[next]) {
                        queue.add(next);
                    }
                }
            }

            // 현재 처리 불가능 -> queue에 다시 넣어서 맨 뒤로
            else {
                queue.add(now);
            }
        }
    }
}
