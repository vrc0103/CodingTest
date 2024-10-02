import java.io.*;
import java.util.*;

public class BOJ3985 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int len;
    static int num;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        int left, right;
        int cnt;
        int idxExpect = 0;
        int idxReal = 0;
        int maxExpect = 0;
        int maxReal = 0;

        len = Integer.parseInt(br.readLine().trim());
        num = Integer.parseInt(br.readLine().trim());

        selected = new boolean[len + 1];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            left = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());

            cnt = 0;

            for (int j = left; j <= right; j++) {
                if (!selected[j]) {
                    selected[j] = true;
                    cnt++;
                }
            }

            // 기댓값이 가장 큰 사람 번호
            if (right - left + 1 > maxExpect) {
                maxExpect = right - left + 1;
                idxExpect = i + 1;
            }

            if (cnt > maxReal) {
                maxReal = cnt;
                idxReal = i + 1;
            }
        }

        sb.append(String.format("%d\n%d\n", idxExpect, idxReal));
    }
}
