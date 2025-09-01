import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int len;
    static int[] height;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        height = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < len; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int[] Rx = new int[len];
        ArrayDeque<int[]> stack = new ArrayDeque<>(); // 높이, 송신 위치

        for (int i = len - 1; i >= 0; i--) {
            int[] now = { i, height[i] };

            while (!stack.isEmpty() && stack.peek()[1] < now[1]) {
                int[] prev = stack.pop();

                Rx[prev[0]] = i + 1;
            }

            stack.push(now);
        }

        for (int i = 0; i < len; i++) {
            sb.append(Rx[i]).append(" ");
        }
        sb.append("\n");
    }
}
