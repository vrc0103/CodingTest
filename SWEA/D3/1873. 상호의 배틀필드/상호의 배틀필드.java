import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int row, col;
    static int[] now;       // row, col, dir
    static char[][] map;
    static char[] cmds = {'R', 'D', 'L', 'U'};
    static char[] tank = {'>', 'v', '<', '^'};

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");

            getRes();
        }

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        // 정보
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        now = new int[3];

        for(int r = 0; r < row; r++) {
            String input = br.readLine().trim();

            for(int c = 0; c < col; c++) {
                map[r][c] = input.charAt(c);

                for(int i = 0; i < 4; i++) {
                    if(map[r][c] == tank[i]) {
                        now[0] = r;
                        now[1] = c;
                        now[2] = i;
                    }
                }
            }
        }

        // 명령어 수행
        int cnt = Integer.parseInt(br.readLine().trim());
        String str = br.readLine().trim();
        for(int i = 0; i < cnt; i++){
            char cmd = str.charAt(i);

            if(cmd == 'S') {
                shot();
            } else {
                for(int j = 0; j < 4; j++) {
                    if(cmd == cmds[j]) {
                        move(j);

                        break;
                    }
                }
            }
        }

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
    }

    static void shot() {
        int dir = now[2];
        int nextR = now[0] + dR[dir];
        int nextC = now[1] + dC[dir];

        while(nextR >= 0 && nextR < row && nextC >= 0 && nextC < col) {
            // 벽돌 벽 파괴
            if(map[nextR][nextC] == '*') {
                map[nextR][nextC] = '.';
                break;
            }
            // 강철 벽은 파괴 불가
            else if (map[nextR][nextC] == '#') {
                break;
            }
            // 나머지는 통과
            else {
                nextR += dR[dir];
                nextC += dC[dir];
            }
        }
    }

    static void move(int dir) {
        now[2] = dir;
        int nextR = now[0] + dR[dir];
        int nextC = now[1] + dC[dir];

        if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
            // 맵 끝이어도 방향은 전환
            map[now[0]][now[1]] = tank[dir];

            return;
        }

        // 평지면 이동
        if(map[nextR][nextC] == '.') {
            map[now[0]][now[1]] = '.';
            map[nextR][nextC] = tank[dir];
            now[0] = nextR;
            now[1] = nextC;
        }
        // 아니면 방향 전환만
        else {
            map[now[0]][now[1]] = tank[dir];
        }
    }
}
