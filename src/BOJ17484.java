import java.io.*;
import java.util.*;

public class BOJ17484 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int row, col;
    static int[][] fuel;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        getInfo();

        getRes();

        System.out.println(res);
    }

    static void getInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        fuel = new int[row + 1][col];

        for (int r = 1; r <= row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < col; c++) {
                fuel[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // https://velog.io/@gkdbssla97/BOJ-17484.-%EC%A7%84%EC%9A%B0%EC%9D%98-%EB%8B%AC-%EC%97%AC%ED%96%89-Small-Java
    static void getRes() {
        // 세 번째 인자 : 지나온 경로
        // 0 : 오른쪽 위 -> 왼쪽 아래
        // 1 : 위 -> 아래
        // 2 : 왼쪽 위 -> 오른쪽 아래
        dp = new int[row + 1][col][3];

        // 첫 줄은 그대로
        for (int c = 0; c < col; c++) {
            dp[1][c][0] = fuel[1][c];
            dp[1][c][1] = fuel[1][c];
            dp[1][c][2] = fuel[1][c];
        }

        for (int r = 2; r <= row; r++) {
            for (int c = 0; c < col; c++) {
                // 맨 왼쪽 : 왼쪽에서 진입 불가능
                if (c == 0) {
                    // / 방향 진입 : | 방향과 \ 방향 중 누적 연료의 최솟값 + 현재 칸의 연료
                    dp[r][c][0] = Math.min(dp[r - 1][c + 1][1], dp[r - 1][c + 1][2]) + fuel[r][c];

                    // | 방향 진입 : / 방향의 누적 연료 + 현재 칸의 연료
                    dp[r][c][1] = dp[r - 1][c][0] + fuel[r][c];

                    // \ 방향 진입 불가
                    dp[r][c][2] = Integer.MAX_VALUE;
                }

                // 맨 오른쪽 : 오른쪽에서 진입 불가능
                else if (c == col - 1) {
                    // / 방향 진입 불가
                    dp[r][c][0] = Integer.MAX_VALUE;

                    // | 방향 진입 : \ 방향의 누적 연료 + 현재 칸의 연료
                    dp[r][c][1] = dp[r - 1][c][2] + fuel[r][c];

                    // \ 방향 진입 : / 방향과 | 방향 중 누적 연료의 최솟값 + 현재 칸의 연료
                    dp[r][c][2] = Math.min(dp[r - 1][c - 1][0], dp[r - 1][c - 1][1]) + fuel[r][c];
                }

                // 나머지 : /, |, \ 진입 모두 가능
                else {
                    // / 방향 진입 : | 방향과 \ 방향 중 누적 연료의 최솟값 + 현재 칸의 연료
                    dp[r][c][0] = Math.min(dp[r - 1][c + 1][1], dp[r - 1][c + 1][2]) + fuel[r][c];

                    // | 방향 진입 : / 방향과 \ 방향 중 누적 연료의 최솟값 + 현재 칸의 연료
                    dp[r][c][1] = Math.min(dp[r - 1][c][0], dp[r - 1][c][2]) + fuel[r][c];

                    // \ 방향 진입 : / 방향과 | 방향 중 누적 연료의 최솟값 + 현재 칸의 연료
                    dp[r][c][2] = Math.min(dp[r - 1][c - 1][0], dp[r - 1][c - 1][1]) + fuel[r][c];
                }
            }
        }

        res = Integer.MAX_VALUE;
        for (int c = 0; c < col; c++) {
            for (int d = 0; d < 3; d++) {
                res = Math.min(res, dp[row][c][d]);
            }
        }
    }

    // 실패
    static void getRes_MY() {
        int preC, preC2;
        int sum;
        int[] dC = { -1, 0, 1 };

        dp = new int[row + 1][col][2];

        // 첫 줄은 그대로
        for (int r = 0; r <= 1; r++) {
            for (int c = 0; c < col; c++) {
                dp[r][c][0] = fuel[r][c];
                dp[r][c][1] = -1; // 방향
            }
        }

        for (int r = 2; r <= row; r++) {
            for (int c = 0; c < col; c++) {
                dp[r][c][0] = Integer.MAX_VALUE;

                // 첫 번째 방향 결정
                for (int d1 = 0; d1 < 3; d1++) {
                    preC = c + dC[d1];

                    // 범위를 벗어나면 수행 X
                    if (preC < 0 || preC >= col) {
                        continue;
                    }

                    // 두 번째 방향 결정
                    for (int d2 = 0; d2 < 3; d2++) {
                        preC2 = preC + dC[d2];

                        // 범위를 벗어나거나 방향이 같은 경우, 누적합의 최근 방향과 같은 경우 수행 X
                        if (preC2 < 0 || preC2 >= col || d2 == d1 || d2 == dp[r - 2][preC2][1]) {
                            continue;
                        }

                        sum = fuel[r][c] + fuel[r - 1][preC] + dp[r - 2][preC2][0];

                        if (dp[r][c][0] > sum) {
                            dp[r][c][0] = sum;
                            dp[r][c][1] = d1;
                        }
                    }
                }
            }
        }

        for (int r = 0; r <= row; r++) {
            for (int c = 0; c < col; c++) {
                System.out.printf("%d ", dp[r][c][0]);
            }
            System.out.println();
        }

        res = Integer.MAX_VALUE;
        for (int c = 0; c < col; c++) {
            res = Math.min(res, dp[row][c][0]);
        }
    }
}
