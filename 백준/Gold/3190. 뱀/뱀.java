import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size;
    static char[][] map;
    static int[][] dirs;
    static ArrayDeque<int[]> snake = new ArrayDeque<>();

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        size = Integer.parseInt(br.readLine().trim());
        map = new char[size + 1][size + 1];

        for(char[] tmp : map) {
            Arrays.fill(tmp, '.');
        }

        int cnt = Integer.parseInt(br.readLine().trim());
        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            map[row][col] = 'a';
        }

        cnt = Integer.parseInt(br.readLine().trim());
        dirs = new int[cnt][2];
        for(int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int time = Integer.parseInt(st.nextToken());
            char cmd = st.nextToken().charAt(0);

            dirs[i][0] = time;

            if(cmd == 'L') {
                dirs[i][1] = 3;
            } else {
                dirs[i][1] = 1;
            }
        }
    }

    static void getRes() {
        int dir = 0;
        int now = 0;

        snake.add(new int[] {1, 1});
        map[1][1] = 's';

        while(true) {
            // System.out.println(res);
            // for (char[] tmp : map) {
            //     System.out.println(Arrays.toString(tmp));
            // }
            // System.out.println();

            // 시간 되면 방향 전환
            if(now < dirs.length && dirs[now][0] == res) {
                dir = (dir + dirs[now][1]) % 4;
                now++;
            }

            res++;

            int nextR = snake.peek()[0] + dR[dir];
            int nextC = snake.peek()[1] + dC[dir];

            if(nextR <= 0 || nextR > size || nextC <= 0 || nextC > size || map[nextR][nextC] == 's') {
                break;
            }

            // 사과 못먹으면 꼬리 짧아짐
            if (map[nextR][nextC] != 'a') {
                int[] rmv = snake.removeLast();
                map[rmv[0]][rmv[1]] = '.';
            }

            // 한 칸 이동
            snake.addFirst(new int[] {nextR, nextC});
            map[nextR][nextC] = 's';
        }
    }
}
