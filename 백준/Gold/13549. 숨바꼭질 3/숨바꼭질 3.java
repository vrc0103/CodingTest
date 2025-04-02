import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int now, target;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine().trim());
        now = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        cnt = new int[100_001];
        Arrays.fill(cnt, Integer.MAX_VALUE);

        q.offer(now);
        cnt[now] = 0;

        while(true) {
            int now = q.poll();

            if(now == target) {
                res = cnt[now];
                break;
            }

            int idx = now * 2;

            while(idx != 0 && idx <= 100_000) {
                if(cnt[idx] > cnt[now]) {
                    q.offer(idx);
                    cnt[idx] = cnt[now];
                }

                idx *= 2;
            }

            if(now < 100_000 && cnt[now + 1] > cnt[now] + 1) {
                q.add(now + 1);
                cnt[now + 1] = cnt[now] + 1;
            }

            if(now > 0 && cnt[now - 1] > cnt[now] + 1) {
                q.add(now - 1);
                cnt[now - 1] = cnt[now] + 1;
            }
        }
    }
}
