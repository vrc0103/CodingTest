import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int num;
    static int res;
    static int[][] val;

    public static void main(String[] args) throws IOException {
        getVal();

        System.out.println(res);
    }

    static void getVal() throws IOException {
        num = Integer.parseInt(br.readLine().trim()) + 1; // 1 ~ N으로 계산

        val = new int[num][3];

        for (int i = 1; i < num; i++) {
            st = new StringTokenizer(br.readLine().trim());

            // 1번 집부터 색깔이 연속되지 않도록 선택하는 비용 누적
            val[i][0] = Integer.parseInt(st.nextToken()) + Math.min(val[i - 1][1], val[i - 1][2]);
            val[i][1] = Integer.parseInt(st.nextToken()) + Math.min(val[i - 1][0], val[i - 1][2]);
            val[i][2] = Integer.parseInt(st.nextToken()) + Math.min(val[i - 1][0], val[i - 1][1]);
        }

        // 배열의 마지막 행(최종 누적 합) 중 최솟값 선택
        res = Math.min(val[num - 1][0], Math.min(val[num - 1][1], val[num - 1][2]));
    }
}
