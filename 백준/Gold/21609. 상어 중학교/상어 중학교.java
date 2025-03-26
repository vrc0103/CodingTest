import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size, color;
    static int[][] cube;

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
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        color = Integer.parseInt(st.nextToken());

        cube = new int[size][size];
        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < size; c++) {
                cube[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        while(true) {
            // 1. 가장 큰 블록 집합 찾기
            ArrayDeque<int[]> rmv = new ArrayDeque<>();
            boolean[][] checked = new boolean[size][size];
            int cnt = 0;
            int rainbow = 0;

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {

                    if(cube[r][c] > 0 && !checked[r][c]) {
                        int num = cube[r][c];
                        ArrayDeque<int[]> flood = new ArrayDeque<>();
                        ArrayDeque<int[]> tmp = new ArrayDeque<>();
                        ArrayDeque<int[]> zero = new ArrayDeque<>();

                        flood.offer(new int[] {r, c});
                        checked[r][c] = true;
                        while(!flood.isEmpty()) {
                            int[] now = flood.poll();
                            tmp.offer(now);

                            for(int i = 0; i < 4; i++) {
                                int nextR = now[0] + dR[i];
                                int nextC = now[1] + dC[i];

                                if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size || checked[nextR][nextC]) {
                                    continue;
                                }

                                if(cube[nextR][nextC] == num) {
                                    flood.offer(new int[] {nextR, nextC});
                                    checked[nextR][nextC] = true;
                                }

                                if(cube[nextR][nextC] == 0) {
                                    zero.offer(new int[] {nextR, nextC});
                                    flood.offer(new int[] { nextR, nextC });
                                    checked[nextR][nextC] = true;
                                }
                            }
                        }

                        /*
                         * 1. 더 큰 블록 집합으로 갱신
                         * 2. 동일 크기이면 0번 블록 많은 집합 선택
                         * 3. 이중 for문 -> 행, 열 작은 순서 적용됨
                         */
                        if (cnt < tmp.size()) {
                            cnt = tmp.size();
                            rainbow = zero.size();
                            rmv = tmp;
                        } else if(cnt == tmp.size() && rainbow < zero.size()) {
                            rainbow = zero.size();
                            rmv = tmp;
                        } else if(cnt == tmp.size() && rainbow == zero.size()) {
                            rmv = tmp;
                        }

                        // 0은 다음 탐색에도 포함시키도록 다시 false로
                        while(!zero.isEmpty()) {
                            int[] now = zero.poll();
                            checked[now[0]][now[1]] = false;
                        }
                    }
                }
            }

            if(cnt < 2) {
                break;
            }

            // 2. 블럭 제거 및 점수 계산
            while(!rmv.isEmpty()) {
                int[] now = rmv.poll();
                cube[now[0]][now[1]] = -2;
            }

            res += cnt * cnt;

            // 3. 중력 적용
            gravity();

            // 4. 반시계 회전
            int[][] newCube = new int[size][size];

            for(int r = 0; r < size; r++) {
                for(int c = 0; c < size; c++) {
                    newCube[r][c] = cube[c][size - 1 - r];
                }
            }

            cube = newCube;

            // 5. 중력
            gravity();
        }

    }

    static void gravity() {
        for(int c = 0; c < size; c++) {
            int idx = size - 1;

            for(int r = size - 1; r >= 0; r--) {
                // 빈 공간 찾기
                while(r >= 0 && cube[r][c] != -2) {
                    r--;
                }

                idx = Math.min(r, idx);

                // 내려보낼 블럭 찾기
                while(idx >= 0 && cube[idx][c] == -2) {
                    idx--;
                }

                if(r < 0 || idx < 0) {
                    break;
                }

                if(cube[idx][c] == -1) {
                    r = idx;
                } else {
                    cube[r][c] = cube[idx][c];
                    cube[idx][c] = -2;
                }

                idx--;
            }
        }
    }
}
