import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col;
    static char[][] map;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][];

        for(int r = 0; r < row; r++) {
            map[r] = br.readLine().trim().toCharArray();
        }
    }

    static void getRes() {
        List<Character> check = new ArrayList<>();
        check.add(map[0][0]);

        move(0, 0, 1, check);
    }

    static void move(int nowR, int nowC, int cnt, List<Character> check) {
        res = Math.max(res, cnt);

        for(int i = 0; i < 4; i++) {
            int nextR = nowR + dR[i];
            int nextC = nowC + dC[i];

            if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                continue;
            }

            int idx = Collections.binarySearch(check, map[nextR][nextC]);

            if(idx >= 0) {
                continue;
            }

            idx = idx * (-1) - 1;
            check.add(idx, map[nextR][nextC]);
            move(nextR, nextC, cnt + 1, check);
            check.remove(idx);
        }
    }
}
