import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col;
    static char[][] board;

    static char[][][] chess = { {
            "BWBWBWBW".toCharArray(),
            "WBWBWBWB".toCharArray(),
            "BWBWBWBW".toCharArray(),
            "WBWBWBWB".toCharArray(),
            "BWBWBWBW".toCharArray(),
            "WBWBWBWB".toCharArray(),
            "BWBWBWBW".toCharArray(),
            "WBWBWBWB".toCharArray()
    }, {
            "WBWBWBWB".toCharArray(),
            "BWBWBWBW".toCharArray(),
            "WBWBWBWB".toCharArray(),
            "BWBWBWBW".toCharArray(),
            "WBWBWBWB".toCharArray(),
            "BWBWBWBW".toCharArray(),
            "WBWBWBWB".toCharArray(),
            "BWBWBWBW".toCharArray()
    } };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        res = 64;

        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        board = new char[row][col];

        for(int i = 0; i < row; i++) {
            board[i] = br.readLine().trim().toCharArray();
        }
    }

    static void getRes() {
        // 입력받은 보드
        for(int r = 0; r < row - 7; r++) {
            for(int c = 0; c < col - 7; c++) {
                // 체스판 패턴 2개
                for (int k = 0; k < 2; k++) {
                    int cnt = 0;

                    for(int i = 0; i < 8; i++) {
                        for(int j = 0; j < 8; j++) {
                            if(board[r + i][c + j] != chess[k][i][j]) {
                                cnt++;
                            }
                        }
                    }

                    res = Math.min(res, cnt);
                }
            }
        }
    }
}
