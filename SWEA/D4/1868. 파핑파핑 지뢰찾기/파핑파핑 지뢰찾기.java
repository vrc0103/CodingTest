import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int size;
    static char[][] map;
    static int[][] count;

    static int[] dR = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dC = {1, 1, 0, -1, -1, -1, 0, 1};

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
        size = Integer.parseInt(br.readLine().trim());
        map = new char[size][size];
        count = new int[size][size];

        for(int r = 0; r < size; r++) {
            map[r] = br.readLine().trim().toCharArray();
        }
    }

    static void getRes() {
        res = 0;

        // 격자별 숫자 계산
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if(map[r][c] == '*') {
                    count[r][c] = -1;

                    continue;
                }

                int cnt = 0;

                for(int i = 0; i < 8; i++) {
                    int nextR = r + dR[i];
                    int nextC = c + dC[i];

                    if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                        continue;
                    }

                    if(map[nextR][nextC] == '*') {
                        cnt++;
                    }
                }

                count[r][c] = cnt;
            }
        }

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if(count[r][c] == 0) {
                    res++;

                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    q.add(new int[] {r, c});
                    count[r][c] = -1;

                    while(!q.isEmpty()) {
                        int[] now = q.remove();

                        for(int i = 0; i < 8; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                                continue;
                            }

                            if(count[nextR][nextC] == 0) {
                                q.add(new int[] {nextR, nextC});
                            }

                            count[nextR][nextC] = -1;
                        }
                    }
                }
            }
        }

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if(count[r][c] > 0) {
                    res++;
                }
            }
        }
    }
}
