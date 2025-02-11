import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int start, goal;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int now;
        int[] len = new int[100001];
        Queue<Integer> queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        Arrays.fill(len, -1);
        queue.add(start);
        len[start] = 0;

        while (true) {
            now = queue.remove();

            if (now == goal) {
                res = len[now];
                return;
            }

            if (now + 1 <= 100000 && len[now + 1] == -1) {
                len[now + 1] = len[now] + 1;
                queue.add(now + 1);
            }
            if (now - 1 >= 0 && len[now - 1] == -1) {
                len[now - 1] = len[now] + 1;
                queue.add(now - 1);
            }
            if (now * 2 <= 100000 && len[now * 2] == -1) {
                len[now * 2] = len[now] + 1;
                queue.add(now * 2);
            }
        }
    }
}
