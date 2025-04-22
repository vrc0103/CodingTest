import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int row, col;
    static int[][] map;
    
    static int[] wall = {1, 2, 4, 8};
    static int[] dR = {0, -1, 0, 1};
    static int[] dC = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        for(int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int idx = 0;
        int maxSize = 0;
        int[][] area = new int[row][col];
        List<Integer> size = new ArrayList<>();

        size.add(0);
        
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(area[r][c] == 0) {
                    idx++;
                    int cnt = 0;

                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {r, c});
                    area[r][c] = idx;

                    while(!q.isEmpty()) {
                        int[] now = q.poll();
                        cnt++;

                        for(int i = 0; i < 4; i++) {
                            // 벽 처리
                            if((map[now[0]][now[1]] & wall[i]) == wall[i]) {
                                continue;
                            }

                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if(area[nextR][nextC] == 0) {
                                q.offer(new int[] {nextR, nextC});
                                area[nextR][nextC] = idx;
                            }
                        }
                    }

                    size.add(cnt);
                    maxSize = Math.max(maxSize, cnt);
                }
            }
        }

        int maxMerge = 0;
        List<HashSet<Integer>> near = new ArrayList<>();

        for(int i = 0; i <= idx; i++) {
            near.add(new HashSet<>());
        }

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                for(int i = 0; i < 4; i++) {
                    int nextR = r + dR[i];
                    int nextC = c + dC[i];

                    if(nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && area[nextR][nextC] != area[r][c]) {
                        near.get(area[r][c]).add(area[nextR][nextC]);
                    }
                }
            }
        }

        for(int i = 1; i <= idx; i++) {
            int sum = size.get(i);

            for(int j : near.get(i)) {
                maxMerge = Math.max(maxMerge, sum + size.get(j));
            }
        }

        sb.append(idx).append("\n").append(maxSize).append("\n").append(maxMerge).append("\n");
    }
}
