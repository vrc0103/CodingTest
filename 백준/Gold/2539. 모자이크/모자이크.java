import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col;
    static int num;
    static int minS;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        num = Integer.parseInt(br.readLine().trim());
        int cnt = Integer.parseInt(br.readLine().trim());

        List<Integer> cols = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            minS = Math.max(minS, r);
            cols.add(c);
        }

        Collections.sort(cols);

        // 중복 제거
        list = new ArrayList<>();
        list.add(cols.remove(0));

        while (cols.size() > 0) {
            int now = cols.remove(0);

            if (list.get(list.size() - 1) < now) {
                list.add(now);
            }
        }
    }

    static void getRes() {
        int left = minS;
        int right = col;

        while (left <= right) {
            int loc = list.get(0);
            int cnt = 1;
            int mid = (left + right) / 2;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) - loc < mid) {
                    continue;
                }

                loc = list.get(i);
                cnt++;
            }

            if (cnt <= num) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
    }
}
