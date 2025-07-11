import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, M;
    static int[] nums;
    static int[] permutation;
    static TreeSet<String> set;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        // 중복 순열인데 결과가 비내림차순(같거나 커지는 순열)이어야 함
        set = new TreeSet<>();

        Arrays.sort(nums);
        permutation = new int[M];

        perm(0, 0);
    }

    static void perm(int cnt, int idx) {
        if (cnt == M) {
            StringBuilder tmp = new StringBuilder();

            for (int i = 0; i < M; i++) {
                tmp.append(permutation[i]).append(" ");
            }

            String now = tmp.toString().trim();

            if (!set.contains(now)) {
                set.add(now);
                sb.append(now).append("\n");
            }

            return;
        }

        for (int i = idx; i < N; i++) {
            permutation[cnt] = nums[i];

            perm(cnt + 1, i);
        }
    }
}
