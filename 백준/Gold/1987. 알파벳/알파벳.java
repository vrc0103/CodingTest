import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col;
    static char[][] map;
    static boolean[] visited;

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
        visited = new boolean[26];
        visited[map[0][0] - 'A'] = true; 

        move(0, 0, 1);
    }

    static void move(int nowR, int nowC, int cnt) {
        res = Math.max(res, cnt);

        for(int i = 0; i < 4; i++) {
            int nextR = nowR + dR[i];
            int nextC = nowC + dC[i];

            if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col || visited[map[nextR][nextC] - 'A']) {
                continue;
            }

            visited[map[nextR][nextC] - 'A'] = true;
            move(nextR, nextC, cnt + 1);
            visited[map[nextR][nextC] - 'A'] = false;
        }
    }
}
