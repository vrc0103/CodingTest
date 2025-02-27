import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int row, col;
    static char[][] map;
    static ArrayDeque<int[]> fire, jh;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        fire = new ArrayDeque<>();
        jh = new ArrayDeque<>();

        for(int r = 0; r < row; r++) {
            String input = br.readLine().trim();

            for(int c = 0; c < col; c++) {
                map[r][c] = input.charAt(c);

                if(map[r][c] == 'J') {
                    jh.add(new int[] {r, c});
                } else if(map[r][c] == 'F') {
                    fire.add(new int[] {r, c});
                }
            }
        }
    }

    static void getRes() {
        while(true) {
            res++;

            // 이번 사이클에 번지는 불 위치
            int lenF = fire.size();

            while(lenF-- > 0) {
                int[] nowF = fire.remove();

                for(int i = 0; i < 4; i++) {
                    int nextFR = nowF[0] + dR[i];
                    int nextFC = nowF[1] + dC[i];

                    if(nextFR < 0 || nextFR >= row || nextFC < 0 || nextFC >= col || map[nextFR][nextFC] == '#' || map[nextFR][nextFC] == 'F') {
                        continue;
                    }

                    map[nextFR][nextFC] = 'F';
                    fire.add(new int[] {nextFR, nextFC});
                }
            }

            // 지훈의 이동 가능 위치
            int lenJ = jh.size();

            while(lenJ-- > 0) {
                int[] nowJ = jh.remove();

                // 이번 사이클에 탈출 가능
                if(nowJ[0] == 0 || nowJ[0] == row - 1 || nowJ[1] == 0 || nowJ[1] == col - 1) {
                    sb.append(res);

                    return;
                }

                for(int i = 0; i < 4; i++) {
                    int nextJR = nowJ[0] + dR[i];
                    int nextJC = nowJ[1] + dC[i];

                    if(map[nextJR][nextJC] == '#' || map[nextJR][nextJC] == 'F' || map[nextJR][nextJC] == 'J') {
                        continue;
                    }

                    map[nextJR][nextJC] = 'J';
                    jh.add(new int[] {nextJR, nextJC});
                }
            }

            // System.out.println(res);
            // for(char[] tmp : map) {
            //     System.out.println(Arrays.toString(tmp));
            // }

            // 이동 가능 위치가 없음 = 미로 탈출 불가
            if(jh.size() == 0) {
                sb.append("IMPOSSIBLE");

                return;
            }
        }
    }
}