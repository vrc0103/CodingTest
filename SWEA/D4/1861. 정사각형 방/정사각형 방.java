import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int resNum, resCnt;
    static int size;
    static int[][] map;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(resNum).append(" ").append(resCnt).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        resNum = 1;
        resCnt = 1;
        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];

        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                int cnt = 1;
                int nowR = r;
                int nowC = c;

                loop: while(true) {
                    for(int i = 0; i < 4; i++) {
                        int nextR = nowR + dR[i];
                        int nextC = nowC + dC[i];

                        if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                            continue;
                        }

                        if(map[nextR][nextC] == map[nowR][nowC] + 1) {
                            nowR = nextR;
                            nowC = nextC;
                            cnt++;

                            continue loop;
                        }
                    }

                    break;
                }

                if(resCnt < cnt) {
                    resCnt = cnt;
                    resNum = map[r][c];
                } else if(resCnt == cnt) {
                    resNum = Math.min(resNum, map[r][c]);
                }
            }
        }
    }
}
