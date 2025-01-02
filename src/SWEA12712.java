import java.io.*;
import java.util.*;

public class SWEA12712 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int size, power;
    static int res;
    static int[][] fly;

    static int[] dR = { 0, 1, 0, -1, -1, 1, 1, -1 };
    static int[] dC = { 1, 0, -1, 0, 1, 1, -1, -1 };

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb.toString());
    }

    static void setInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        power = Integer.parseInt(st.nextToken());

        fly = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int j = 0; j < size; j++) {
                fly[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int sum1, sum2;
        int nextR, nextC;

        res = 0;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                sum1 = sum2 = fly[r][c];

                // 살충제 범위
                for (int pow = 1; pow < power; pow++) {
                    for (int i = 0; i < 8; i++) {
                        nextR = r + pow * dR[i];
                        nextC = c + pow * dC[i];

                        if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                            continue;
                        }

                        // 십자
                        if (i < 4) {
                            sum1 += fly[nextR][nextC];
                        }
                        // X자
                        else {
                            sum2 += fly[nextR][nextC];
                        }
                    }
                }

                res = Math.max(res, Math.max(sum1, sum2));
            }
        }
    }
}
