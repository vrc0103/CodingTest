import java.util.*;

class Solution {
    static int row, col;
    static char[][] map;
    static boolean[][] empty;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public int solution(String[] storage, String[] requests) {
        int answer = 0;

        row = storage.length;
        col = storage[0].length();
        map = new char[row][col];
        empty = new boolean[row][col];

        for(int r = 0; r < row; r++) {
            String str = storage[r];

            for(int c = 0; c < col; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        int len = requests.length;
        for(int i = 0; i < len; i++) {
            String cmd = requests[i];
            if(cmd.length() == 1) {
                forklift(cmd.charAt(0));
            } else {
                crane(cmd.charAt(0));
            }
        }

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(map[r][c] != 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    static void crane(char ch) {
        System.out.printf("crane c : %c\n", ch);

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(map[r][c] == ch) {
                    map[r][c] = 0;
                    empty[r][c] = true;

                    System.out.printf("crane : %d %d\n", r, c);
                }
            }
        }
    }

    static void forklift(char ch) {
        System.out.printf("fork c : %c\n", ch);

        ArrayDeque<int[]> mt = new ArrayDeque<>();
        ArrayDeque<int[]> select = new ArrayDeque<>();

        boolean[][] visited = new boolean[row][col];

        for(int r = 0; r < row; r++) {
            if(empty[r][0]) {
                mt.offer(new int[] {r, 0});
                visited[r][0] = true;
            } else if(map[r][0] == ch) {
                select.offer(new int[] {r, 0});
                visited[r][0] = true;
            }

            if(empty[r][col - 1]) {
                mt.offer(new int[] {r, col - 1});
                visited[r][col - 1] = true;
            } else if(map[r][col - 1] == ch) {
                select.offer(new int[] {r, col - 1});
                visited[r][col - 1] = true;
            }
        }

        for(int c = 1; c < col - 1; c++) {
            if(empty[0][c]) {
                mt.offer(new int[] {0, c});
                visited[0][c] = true;
            } else if(map[0][c] == ch) {
                select.offer(new int[] {0, c});
                visited[0][c] = true;
            }

            if(empty[row - 1][c]) {
                mt.offer(new int[] {row - 1, c});
                visited[row - 1][c] = true;
            } else if(map[row - 1][c] == ch) {
                select.offer(new int[] {row - 1, c});
                visited[row - 1][c] = true;
            }
        }

        while(!mt.isEmpty()) {
            int[] now = mt.poll();

            for(int i = 0; i < 4; i++) {
                int nextR = now[0] + dR[i];
                int nextC = now[1] + dC[i];

                if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }

                if(empty[nextR][nextC] && !visited[nextR][nextC]) {
                    mt.offer(new int[] {nextR, nextC});
                    visited[nextR][nextC] = true;
                }

                else if(!visited[nextR][nextC] && map[nextR][nextC] == ch) {
                    select.offer(new int[] {nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }

        while(!select.isEmpty()) {
            int now[] = select.poll();

            map[now[0]][now[1]] = 0;
            empty[now[0]][now[1]] = true;

            System.out.printf("fork : %d %d\n", now[0], now[1]);
        }


    }
}