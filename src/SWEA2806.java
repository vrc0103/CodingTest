/*
 * N x N 크기의 체스판에 N개의 퀸을 서로 공격하지 못하도록 배치하는 경우의 수
 *  -> 하나의 퀸이 배치되면 동일한 행, 열, 대각선에는 배치할 수 없음
 */

import java.io.*;

public class SWEA2806 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int num;
    static int res;
    static boolean[][] board;

    static void selectLoc(int col) {
        if (col == num) { // 퀸 N개의 배치가 모두 완료되면 재귀 종료
            res++;
            return;
        }

        if (col == 0) {
            for (int row = 0; row < num; row++) {
                board[row][col] = true;
                selectLoc(col + 1);
                board[row][col] = false;
            }
        } else {
            boolean queenChk;

            for (int row = 0; row < num; row++) {
                queenChk = false;

                for (int chk = 1; chk <= col; chk++) {
                    if (board[row][col - chk]) { // 해당 행에 다른 퀸 존재
                        queenChk = true;
                        break;
                    }
                    if (row - chk >= 0 && board[row - chk][col - chk]) { // 대각선\ 방향에 퀸 존재
                        queenChk = true;
                        break;
                    }
                    if (row + chk < num && board[row + chk][col - chk]) { // 대각선/ 방향에 퀸 존재
                        queenChk = true;
                        break;
                    }

                }
                if (queenChk) {
                    continue;
                }

                // continue에 걸리지 않음 = 공격 가능한 다른 퀸 없는 경우
                board[row][col] = true;
                selectLoc(col + 1);
                board[row][col] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            res = 0;
            num = Integer.parseInt(br.readLine().trim());
            board = new boolean[num][num];

            selectLoc(0);

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb);
    }
}
