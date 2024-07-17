import java.io.*;
import java.util.*;

public class BJ14501 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int days;
    static int[] len, cost;
    static int res;

    public static void main(String[] args) throws IOException {
        getDays();

        getMax(0, 0, true);
        getMax(0, 0, false);

        System.out.println(res);
    }

    static void getDays() throws IOException {
        res = 0;
        days = Integer.parseInt(br.readLine());
        len = new int[days];
        cost = new int[days];

        for (int i = 0; i < days; i++) {
            st = new StringTokenizer(br.readLine());
            len[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getMax(int start, int pay, boolean select) {
        if (start == days) {
            res = Math.max(res, pay);
            return;
        }

        if (select && start + len[start] - 1 < days) {
            // 상담을 받을 예정이고, 퇴사 전까지 완료 가능한 상담
            pay += cost[start];

            start += len[start] - 1; // start를 상담 종료일로 변경
        }

        getMax(start + 1, pay, true);
        getMax(start + 1, pay, false);
    }
}
