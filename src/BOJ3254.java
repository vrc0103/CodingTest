import java.io.*;
import java.util.*;

public class BOJ3254 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int numS, numJ;
    static char[][] map;

    static int[] dR = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int[] dC = { 1, 1, 0, -1, -1, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        char winner = 0;
        int count = 0;

        map = new char[8][8];
        Arrays.fill(map[7], (char) 1);

        for (int t = 1; t <= 21; t++) {
            st = new StringTokenizer(br.readLine().trim());
            numS = Integer.parseInt(st.nextToken());
            numJ = Integer.parseInt(st.nextToken());

            // 김밥이 멈출 때까지 내려감
            if (winner == 0) {
                winner = throwKB('S');
            }

            if (winner == 0) {
                winner = throwKB('J');
            }

            if (winner != 0 && count == 0) {
                count = t;
            }
        }

        if (count == 0) {
            sb.append("ss");
        } else {
            sb.append(winner == 'S' ? "sk " : "ji ").append(count);
        }
        System.out.println(sb);
    }

    static char throwKB(char ch) {
        int row, col;

        col = ch == 'S' ? numS : numJ;

        for (row = 1; row <= 6; row++) {
            if (map[row + 1][col] != 0) {
                map[row][col] = ch;
                break;
            }
        }

        int nextR, nextC;
        int len;

        for (int i = 0; i < 4; i++) {
            len = 1;

            for (int j = 1; j < 4; j++) {
                nextR = row + j * dR[i];
                nextC = col + j * dC[i];

                // 범위를 벗어나거나 연결이 끊기면 중단
                if (nextR < 1 || nextR > 6 || nextC < 1 || nextC > 7 || map[nextR][nextC] != map[row][col]) {
                    break;
                }

                len++;
            }

            for (int j = 1; j < 4; j++) {
                nextR = row + j * dR[i + 4];
                nextC = col + j * dC[i + 4];

                // 범위를 벗어나거나 연결이 끊기면 중단
                if (nextR < 1 || nextR > 6 || nextC < 1 || nextC > 7 || map[nextR][nextC] != map[row][col]) {
                    break;
                }

                len++;
            }

            // 김밥 4개가 연결됨
            if (len >= 4) {
                return ch;
            }
        }

        return 0;
    }
}
