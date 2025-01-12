import java.io.*;
import java.util.*;

public class BOJ1063 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int count;

    static int[] dX = { 1, -1, 0, 0, 1, -1, 1, -1 };
    static int[] dY = { 0, 0, -1, 1, 1, 1, -1, -1 };
    static String[] dir = { "R", "L", "B", "T", "RT", "LT", "RB", "LB" };

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        String oper;
        String locK, locS;
        int[] nowK = new int[2];
        int[] nowS = new int[2];

        st = new StringTokenizer(br.readLine().trim());
        locK = st.nextToken();
        locS = st.nextToken();
        count = Integer.parseInt(st.nextToken());

        nowK[0] = locK.charAt(0) - 'A';
        nowK[1] = locK.charAt(1) - '1';
        nowS[0] = locS.charAt(0) - 'A';
        nowS[1] = locS.charAt(1) - '1';

        for (int i = 0; i < count; i++) {
            oper = br.readLine().trim();

            move(oper, nowK, nowS);
        }

        sb.append((char) (nowK[0] + 'A')).append(nowK[1] + 1).append("\n")
                .append((char) (nowS[0] + 'A')).append(nowS[1] + 1).append("\n");
    }

    static void move(String oper, int[] nowK, int[] nowS) {
        int[] nextK = new int[2];
        int[] nextS = new int[2];

        for (int i = 0; i < dir.length; i++) {
            // 이동 방향 확인
            if (!oper.equals(dir[i])) {
                continue;
            }

            nextK[0] = nowK[0] + dX[i];
            nextK[1] = nowK[1] + dY[i];

            if (nextK[0] < 0 || nextK[0] >= 8 || nextK[1] < 0 || nextK[1] >= 8) {
                return;
            }

            // 킹의 이동 위치에 돌이 있는지 확인
            if (nextK[0] == nowS[0] && nextK[1] == nowS[1]) {
                nextS[0] = nowS[0] + dX[i];
                nextS[1] = nowS[1] + dY[i];

                if (nextS[0] < 0 || nextS[0] >= 8 || nextS[1] < 0 || nextS[1] >= 8) {
                    return;
                }

                // 있으면 돌도 이동
                nowS[0] = nextS[0];
                nowS[1] = nextS[1];
            }

            nowK[0] = nextK[0];
            nowK[1] = nextK[1];

            return;
        }
    }
}
