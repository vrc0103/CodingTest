import java.io.*;
import java.util.*;

public class BOJ18428 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String res;
    static int size;
    static char[][] map;
    static boolean[][] watch;
    static ArrayList<int[]> teacher = new ArrayList<>();

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {

        size = Integer.parseInt(br.readLine().trim());

        map = new char[size][size];
        watch = new boolean[size][size];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                map[r][c] = st.nextToken().charAt(0);

                if (map[r][c] == 'T') {
                    teacher.add(new int[] { r, c });
                }
            }
        }

        // 풀이
        res = "NO";

        rec(0, 0);

        System.out.println(res);
    }

    static void rec(int loc, int num) {
        if (res.equals("YES")) {
            return;
        }
        if (num == 3) {
            int[] now;
            int nextR, nextC;

            for (int t = 0; t < teacher.size(); t++) {
                now = teacher.get(t);

                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j < 6; j++) {
                        nextR = now[0] + j * dR[i];
                        nextC = now[1] + j * dC[i];

                        if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size || map[nextR][nextC] == 'O') {
                            break;
                        }
                        // 학생 발각
                        else if (map[nextR][nextC] == 'S') {
                            return;
                        }
                    }
                }
            }

            // 모든 선생님의 시야에 학생 X
            res = "YES";
        }
        int row, col;

        for (int i = loc; i < size * size; i++) {
            row = i / size;
            col = i % size;

            if (map[row][col] == 'X') {
                map[row][col] = 'O';
                rec(i + 1, num + 1);
                map[row][col] = 'X';
            }
        }
    }
}
