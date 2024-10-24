import java.io.*;
import java.util.*;

public class BOJ14499 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int row, col;
    static int nowR, nowC;
    static int num;
    static int[][] map;
    static int[] dice; // 주사위 번호는 전개도를 따름 : 1 <-> 6 / 2 <-> 5 / 3 <-> 4
    static int[] idxDice = { 1, 2, 3 }; // 위, 북, 동

    static int[] dR = { 0, 0, 0, -1, 1 };
    static int[] dC = { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        int oper;
        int nextR, nextC;

        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        nowR = Integer.parseInt(st.nextToken());
        nowC = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        dice = new int[7];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < num; i++) {
            oper = Integer.parseInt(st.nextToken());

            nextR = nowR + dR[oper];
            nextC = nowC + dC[oper];

            if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                continue;
            }

            nowR = nextR;
            nowC = nextC;
            moveIdx(oper);

            if (map[nowR][nowC] == 0) {
                map[nowR][nowC] = dice[7 - idxDice[0]];
            } else {
                dice[7 - idxDice[0]] = map[nowR][nowC];
                map[nowR][nowC] = 0;
            }

            sb.append(dice[idxDice[0]]).append("\n");
        }
    }

    static void moveIdx(int oper) {
        int bot;

        // 이동 후 idx
        if (oper == 1) {
            bot = idxDice[2]; // 동 -> 아래
            idxDice[2] = idxDice[0]; // 위 -> 동
            idxDice[0] = 7 - bot; // 서 -> 위
        } else if (oper == 2) {
            bot = 7 - idxDice[2];
            idxDice[2] = 7 - idxDice[0];
            idxDice[0] = 7 - bot;
        } else if (oper == 3) {
            bot = idxDice[1];
            idxDice[1] = idxDice[0];
            idxDice[0] = 7 - bot;
        } else {
            bot = 7 - idxDice[1];
            idxDice[1] = 7 - idxDice[0];
            idxDice[0] = 7 - bot;
        }
    }
}
