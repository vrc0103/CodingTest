import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col;
    static boolean[][] sadari;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        col = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        sadari = new boolean[row + 1][col + 2];

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sadari[r][c] = true;
        }
    }

    static void getRes() {
        int INF = Integer.MAX_VALUE;
        res = INF;

        rec(0, 1, 1);

        if (res == INF) {
            res = -1;
        }
    }

    static void rec(int cnt, int prevR, int prevC) {
        if (cnt > 3) {
            return;
        } else if (isPos()) {
            res = Math.min(res, cnt);

            return;
        }

        for (int r = prevR; r <= row; r++) {
            for (int c = (r == prevR ? prevC : 1); c < col; c++) {
                if (!sadari[r][c]) {
                    if (sadari[r][c] || sadari[r][c - 1] || sadari[r][c + 1]) {
                        continue;
                    }

                    sadari[r][c] = true;
                    rec(cnt + 1, r, c);
                    sadari[r][c] = false;
                }
            }
        }
    }

    static boolean isPos() {
        boolean pos = true;

        for (int c = 1; c <= col; c++) {
            int now = c;

            for (int r = 1; r <= row; r++) {
                if (now == col && sadari[r][now - 1]) {
                    now--;
                } else if (now == 1 && sadari[r][now]) {
                    now++;
                } else if (now > 1 && now < col) {
                    if (sadari[r][now]) {
                        now++;
                    } else if (sadari[r][now - 1]) {
                        now--;
                    }
                }
            }

            if (now != c) {
                pos = false;
                break;
            }
        }

        return pos;
    }
}
