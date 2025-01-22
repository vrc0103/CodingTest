import java.io.*;
import java.util.*;

class BOJ2169 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int row, col;
    static int[][] map;
    static int[][][] sum;

    static int[] dR = { 0, 1, 0 };
    static int[] dC = { 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 정보
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row + 1][col + 1];
        sum = new int[3][row + 1][col + 2]; // 진입 방향

        for (int r = 1; r <= row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 1; c <= col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 풀이
        // DP 계산이 용이하도록 열 범위를 0 ~ col + 1로 생성하였기 때문에
        // 가장자리 값을 큰 음수로 초기화함
        for (int i = 0; i < 3; i++) {
            for (int r = 2; r <= row; r++) {
                for (int c = 0; c <= col + 1; c++) {
                    if (c == 0 || c == col + 1) {
                        sum[i][r][c] = -100_000_000;
                    }
                }
            }
        }

        // 0 : 위 , 1 : 왼쪽 , 2 : 오른쪽
        for (int r = 1; r <= row; r++) {
            if (r == 1) {
                for (int c = 1; c <= col; c++) {
                    sum[0][r][c] = map[r][c] + sum[1][r][c - 1];
                    sum[1][r][c] = map[r][c] + sum[1][r][c - 1];
                    sum[2][r][c] = map[r][c] + sum[1][r][c - 1];
                }
            } else {
                // 위에서 온 경우
                for (int c = 1; c <= col; c++) {
                    sum[0][r][c] = map[r][c] + Math.max(sum[0][r - 1][c], Math.max(sum[1][r - 1][c], sum[2][r - 1][c]));
                }
                // 왼쪽에서 온 경우
                for (int c = 1; c <= col; c++) {
                    sum[1][r][c] = map[r][c] + Math.max(sum[0][r][c - 1], sum[1][r][c - 1]);
                }
                // 오른쪽에서 온 경우
                for (int c = col; c >= 1; c--) {
                    sum[2][r][c] = map[r][c] + Math.max(sum[0][r][c + 1], sum[2][r][c + 1]);
                }
            }

            // for(int c = 1; c <= col; c++) {
            // System.out.printf("%d ", Math.max(sum[0][row][col],
            // Math.max(sum[1][row][col], sum[2][row][col])));
            // }
            // System.out.println();
        }

        res = Math.max(sum[0][row][col], Math.max(sum[1][row][col], sum[2][row][col]));

        System.out.println(res);
    }
}
