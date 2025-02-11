import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int size;
    static int left, right;
    static int[][] map;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        left = Integer.parseInt(st.nextToken());
        right = Integer.parseInt(st.nextToken());

        map = new int[size][size];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int idx;
        int diff;
        int[][] union;
        boolean[][] selected;
        boolean[][] changed = new boolean[size][size];
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<Integer> sum;
        ArrayList<Integer> count;
        boolean break_flag;

        int[] now;
        int nowR, nowC;
        int nextR, nextC;

        for (int r = 0; r < size; r++) {
            Arrays.fill(changed[r], true);
        }

        while (true) {
            idx = 1;
            union = new int[size][size];
            selected = new boolean[size][size];
            sum = new ArrayList<Integer>();
            count = new ArrayList<Integer>();

            sum.add(0); // idx와 맞추기 위해 0번 처리
            count.add(0);
            break_flag = true;

            // 연합
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    // 아직 연합이 없는 국가 중에서 인구이동이 일어난 국가만 탐색(첫 탐색은 모든 국가)
                    if (!selected[r][c] && changed[r][c]) {
                        selected[r][c] = true;
                        union[r][c] = idx;
                        queue.add(new int[] { r, c });
                        sum.add(map[r][c]);
                        count.add(1);

                        // 해당 연합을 BFS로 탐색하며 확장
                        while (!queue.isEmpty()) {
                            now = queue.remove();
                            nowR = now[0];
                            nowC = now[1];

                            for (int d = 0; d < 4; d++) {
                                nextR = nowR + dR[d];
                                nextC = nowC + dC[d];

                                // 범위 밖이거나 이미 연합이 있는 국가는 탐색X
                                if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size
                                        || union[nextR][nextC] > 0) {
                                    continue;
                                }

                                diff = Math.abs(map[nowR][nowC] - map[nextR][nextC]);
                                if (diff >= left && diff <= right) {
                                    selected[nextR][nextC] = true;
                                    union[nextR][nextC] = idx;
                                    queue.add(new int[] { nextR, nextC });

                                    sum.set(idx, sum.get(idx) + map[nextR][nextC]);
                                    count.set(idx, count.get(idx) + 1);
                                }
                            }
                        }

                        idx++;
                    }
                }
            }

            changed = new boolean[size][size];

            for (int i = 1; i < idx; i++) {
                // 연합국이 1개면 탐색X
                if (count.get(i) < 2) {
                    continue;
                }

                for (int r = 0; r < size; r++) {
                    for (int c = 0; c < size; c++) {
                        if (union[r][c] == i) {
                            map[r][c] = sum.get(i) / count.get(i);
                            changed[r][c] = true;

                            // 인구이동 발생 시 다음 탐색 진행
                            break_flag = false;
                        }
                    }
                }
            }

            if (break_flag) {
                break;
            }

            res++;
        }
    }
}
