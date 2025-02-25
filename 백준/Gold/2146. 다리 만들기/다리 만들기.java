import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size;
    static int[][] map;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        // 정보
        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];
        res = size * size;

        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken()) * -1;
            }
        }

        // 풀이
        int cnt = 0;

        // 섬 구분
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if(map[r][c] == -1) {
                    cnt++;
                    ArrayDeque<int[]> queue = new ArrayDeque<>();

                    map[r][c] = cnt;
                    queue.add(new int[] {r, c});

                    while(!queue.isEmpty()) {
                        int[] now = queue.remove();

                        for(int i = 0; i < 4; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size || map[nextR][nextC] >= 0) {
                                continue;
                            }

                            map[nextR][nextC] = cnt;
                            queue.add(new int[] {nextR, nextC});
                        }
                    }
                }
            }
        }

        // for(int[] tmp : map){
        //     System.out.println(Arrays.toString(tmp));
        // }

        boolean[] isle = new boolean[cnt + 1];

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if(map[r][c] > 0 && !isle[map[r][c]]) {
                    boolean[][] checked = new boolean[size][size];

                    isle[map[r][c]] = true;
                    pq.add(new int[] {r, c, 0});

                    loop: while(!pq.isEmpty()) {
                        int[] now = pq.remove();

                        for(int i = 0; i < 4; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size || checked[nextR][nextC]) {
                                continue;
                            }

                            checked[nextR][nextC] = true;
                            
                            // 바다 : 다리 길이 1 증가
                            if(map[nextR][nextC] == 0) {
                                pq.add(new int[] {nextR, nextC, now[2] + 1});
                            }
                            // 동일 섬
                            else if(map[nextR][nextC] == map[r][c]) {
                                pq.add(new int[] {nextR, nextC, 0});
                            }
                            // 다른 섬
                            else {
                                res = Math.min(res, now[2]);
                                pq.clear();
                                break loop;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(res);
    }
}