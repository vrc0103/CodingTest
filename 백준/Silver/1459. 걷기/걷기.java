import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long res = 0;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int cost1 = Integer.parseInt(st.nextToken());
        int cost2 = Integer.parseInt(st.nextToken());
        int diagonal = Math.min(X, Y);

        // 대각선 시간이 가로세로 한 칸보다 짧은 경우
        if (cost2 < cost1) {
            // 대각선으로 이동 시 초과 거리만큼 돌아와야함 -> 짝수 단위
            if (Math.abs(X - Y) % 2 == 1) {
                diagonal += Math.abs(X - Y) - 1;
            } else {
                diagonal += Math.abs(X - Y);
            }

            /*
             * 캐스팅 적용 시 순서 중요
             * (long)으로 캐스팅을 마지막에 적용할 경우 모든 연산이 int로 수행된 후에 메모리만 long으로 할당받게 됨
             * -> 수식 중 하나 이상에 캐스팅을 먼저 적용해야 함
             */
            res = (long) diagonal * cost2 + (long) (Math.abs(X - Y) % 2) * cost1;
        }
        // 대각선 시간이 가로세로 합친 시간보다 짧은 경우
        else if (cost2 < 2 * cost1) {
            res = (long) diagonal * cost2 + (long) (Math.max(X, Y) - diagonal) * cost1;
        }
        // 아니면 그냥 가로세로로만 이동
        else {
            res = (long) (X + Y) * cost1;
        }
    }
}
