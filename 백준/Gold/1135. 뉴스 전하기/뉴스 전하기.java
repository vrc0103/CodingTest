import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num;
    static News[] news;

    static class News {
        int id;
        List<int[]> child;

        public News(int id) {
            this.id = id;
            this.child = new ArrayList<>();
        }

        public int checkAndSort(int idx) {
            News now = news[idx];

            if (now.child.size() == 0) {
                return 0;
            }

            for (int[] next : now.child) {
                next[1] = checkAndSort(next[0]);
            }

            Collections.sort(now.child, (o1, o2) -> o2[1] - o1[1]);

            int need = 0;
            int order = 1;
            for (int[] next : now.child) {
                need = Math.max(need, next[1] + order);
                order++;
            }

            return need;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        st.nextToken();

        news = new News[num];
        news[0] = new News(0);

        for (int i = 0; i < num; i++) {
            news[i] = new News(i);
        }

        for (int i = 1; i < num; i++) {
            int parent = Integer.parseInt(st.nextToken());

            news[i] = new News(i);
            news[parent].child.add(new int[] { i, 0 });
        }
    }

    static void getRes() {
        res = news[0].checkAndSort(0);
    }
}
