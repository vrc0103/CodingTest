import java.io.*;
import java.util.*;

public class BOJ20056 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int size, num, oper;
    static Fireball[][] map;
    static Queue<Fireball> queue = new LinkedList<>();
    static int dirR[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int dirC[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int res;

    static class Fireball {
        int row, col;
        int mass, dir, spd;
        int numOfFb;
        boolean mixed;

        public Fireball(int row, int col, int mass, int spd, int dir) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.spd = spd;
            this.dir = dir;
            this.numOfFb = 1;
            this.mixed = false;
        }
    }

    public static void main(String[] args) throws IOException {
        getFireball();

        System.out.println(res);
    }

    static void getFireball() throws IOException {
        int row, col, mass, spd, dir;
        Fireball fb;

        st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());
        oper = Integer.parseInt(st.nextToken());

        if (num == 0) {
            res = 0;
            return;
        }

        // 처음 파이어볼 정보
        for (int f = 0; f < num; f++) {
            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken()) - 1; // 1 ~ N 이므로 -1
            col = Integer.parseInt(st.nextToken()) - 1;
            mass = Integer.parseInt(st.nextToken());
            spd = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());

            queue.offer(new Fireball(row, col, mass, spd, dir));
        }

        // 이동 명령
        for (int op = 0; op < oper; op++) {
            map = new Fireball[size][size];

            // 이동한 파이어볼을 map에 표시
            while (!queue.isEmpty()) {
                fb = queue.poll();
                mass = fb.mass;
                spd = fb.spd;
                dir = fb.dir;
                row = (fb.row + spd * dirR[dir]) % size;
                col = (fb.col + spd * dirC[dir]) % size;

                // 행, 열의 범위 재지정
                if (row < 0) {
                    row += size;
                }
                if (col < 0) {
                    col += size;
                }

                // 해당 위치에 첫 파이어볼
                if (map[row][col] == null) {
                    map[row][col] = new Fireball(row, col, mass, spd, dir);
                }

                // 다른 파이어볼 존재 -> 중첩
                else {
                    map[row][col].mass += mass;
                    map[row][col].spd += spd;
                    map[row][col].numOfFb++;

                    // 방향의 홀, 짝이 혼합되면 true로 변경
                    if (map[row][col].dir % 2 != dir % 2) {
                        map[row][col].mixed = true;
                    }
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // 파이어볼 존재
                    if (map[i][j] != null) {
                        fb = map[i][j];

                        // 하나만 존재
                        if (fb.numOfFb == 1) {
                            queue.offer(fb);
                        }
                        // 여러 파이어볼 중첩
                        else {
                            mass = fb.mass / 5;
                            spd = fb.spd / fb.numOfFb;

                            // 파이어볼 증발 시 다음 파이어볼 탐색
                            if (mass == 0) {
                                continue;
                            }

                            // 맵의 각 파이어볼마다 4방향으로 분열하여 queue에 저장
                            for (int f = 0; f < 4; f++) {
                                queue.offer(new Fireball(i, j, mass, spd, fb.mixed ? 2 * f + 1 : 2 * f));
                            }
                        }
                    }
                }
            }
        }

        // 최종 남아있는 파이어볼 질량 계산
        res = 0;
        while (!queue.isEmpty()) {
            fb = queue.poll();
            res += fb.mass;
        }
    }
}
