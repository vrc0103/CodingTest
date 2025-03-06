import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int size, time, cnt;
    static ArrayDeque<Group> q;

    static int[] dR = {0, -1, 1, 0, 0};
    static int[] dC = {0, 0, 0, -1, 1};

    static class Group {
        int row, col;
        int vol;
        int dir;

        boolean isDup;
        PriorityQueue<Integer> dup;

        public Group(int row, int col, int vol, int dir) {
            this.row = row;
            this.col = col;
            this.vol = vol;
            this.dir = dir;
            this.isDup = false;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = 0;

        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());

        q = new ArrayDeque<>();

        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int vol = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            q.add(new Group(row, col, vol, dir));
        }
    }

    static void getRes() {
        while(time-- > 0) {
            Group[][] map = new Group[size][size];

            // 이번 사이클에서 각 그룹 이동
            while(!q.isEmpty()) {
                Group now = q.remove();

                now.row += dR[now.dir];
                now.col += dC[now.dir];

                // 경계 밟으면 절반으로 감소 및 방향 반대
                if(now.row == 0 || now.row == size - 1 || now.col == 0 || now.col == size - 1) {
                    now.vol /= 2;
                    
                    if(now.dir % 2 == 0) {
                        now.dir--;
                    } else {
                        now.dir++;
                    }
                }

                if(map[now.row][now.col] == null) {
                    map[now.row][now.col] = now;
                } else {
                    // 첫 중첩이면 PQ 생성
                    if(!map[now.row][now.col].isDup) {
                        map[now.row][now.col].isDup = true;
                        map[now.row][now.col].dup = new PriorityQueue<>(Collections.reverseOrder());
                        map[now.row][now.col].dup.add(map[now.row][now.col].vol);
                    }

                    // 더 큰 군집 들어오면 방향 갱신
                    if(map[now.row][now.col].dup.peek() < now.vol) {
                        map[now.row][now.col].dir = now.dir;
                    }

                    // 군집 추가
                    map[now.row][now.col].dup.add(now.vol);
                }
            }

            // 배양판에서 군집 정보 큐에 추가
            for(int r = 0; r < size; r++) {
                for(int c = 0; c < size; c++) {
                    if(map[r][c] != null) {
                        // 겹친 군집이면 합치기기
                        if(map[r][c].isDup) {
                            map[r][c].vol = 0;
                            map[r][c].isDup = false;

                            while(!map[r][c].dup.isEmpty()) {
                                map[r][c].vol += map[r][c].dup.remove();
                            }
                        }

                        q.add(map[r][c]);
                    }
                }
            }
        }

        while(!q.isEmpty()) {
            res += q.remove().vol;
        }
    }
}
