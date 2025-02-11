import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int num;
    static int row, col;
    static int res;
    static boolean[][] paper;

    public static void main(String[] args) throws IOException {
        getPaper();

        getLen();

        System.out.println(res);
    }

    static void getPaper() throws IOException {
        paper = new boolean[100][100];

        num = Integer.parseInt(br.readLine());
        for (int p = 0; p < num; p++) {
            st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            for (int r = row; r < row + 10; r++) {
                for (int c = col; c < col + 10; c++) {
                    if (!paper[r][c]) {
                        paper[r][c] = true;
                    }
                }
            }
        }
    }

    static void getLen() {
        int[] dirR = { 0, -1, 0, 1 };
        int[] dirC = { 1, 0, -1, 0 };
        int checkR, checkC;

        res = 0;
        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                for (int i = 0; i < 4; i++) {
                    if (paper[r][c]) {
                        checkR = r + dirR[i];
                        checkC = c + dirC[i];

                        // 도화지 경계에 붙은 색종이
                        if (checkR < 0 || checkR == 100 || checkC < 0 || checkC == 100) {
                            res++;
                            continue;
                        }

                        // 색종이 둘레
                        if (!paper[checkR][checkC]) {
                            res++;
                        }
                    }
                }
            }
        }
    }
}
