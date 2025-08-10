import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int size;
    static int blue, white;
    static int[][] color;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        size = Integer.parseInt(br.readLine().trim());

        color = new int[size][size];
        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                color[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        rec(0, 0, size);

        sb.append(white).append("\n").append(blue).append("\n");
    }

    static void rec(int startR, int startC, int len) {
        if (len == 0) {
            return;
        }

        int pivot = color[startR][startC];
        boolean flag = true;

        loop: for (int r = startR; r < startR + len; r++) {
            for (int c = startC; c < startC + len; c++) {
                if (pivot != color[r][c]) {
                    flag = false;
                    break loop;
                }
            }
        }

        if (flag) {
            if (pivot == 1) {
                blue++;
            } else {
                white++;
            }
        } else {
            int half = len / 2;

            rec(startR, startC, half);
            rec(startR + half, startC, half);
            rec(startR, startC + half, half);
            rec(startR + half, startC + half, half);
        }
    }
}
