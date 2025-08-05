import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int target;
    static int[] coin;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int num = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        coin = new int[num];
        for(int i = 0; i < num; i++) {
            coin[i] = Integer.parseInt(br.readLine().trim());
        }
    }

    static void getRes() {
        int[] cnt = new int[target + 1];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] {0, 0});
        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0; i < coin.length; i++) {
                int sum = now[0] + coin[i];

                if(sum <= target && cnt[sum] == 0) {
                    cnt[sum] = now[1] + 1;
                    q.offer(new int[] {sum, cnt[sum]});
                }
            }

            if(cnt[target] > 0) {
                res = cnt[target];
                break;
            }
        }

        if(cnt[target] == 0) {
            res = -1;
        }
    }
}
