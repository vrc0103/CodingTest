import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size;
    static int[][] map;
    static int[][] cnt;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        cnt = new int[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (cnt[r][c] == 0) {
                    res = Math.max(res, find(r, c));
                }
            }
        }
    }

    static int find(int r, int c) {
        if (cnt[r][c] > 0) {
            return cnt[r][c];
        }

        int len = 1;

        for (int i = 0; i < 4; i++) {
            int nextR = r + dR[i];
            int nextC = c + dC[i];

            if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                continue;
            }

            if (map[nextR][nextC] > map[r][c]) {

                len = Math.max(len, find(nextR, nextC) + 1);
            }
        }

        cnt[r][c] = len;

        return cnt[r][c];
    }
}
