import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col;
    static char[][] map;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};
    static char[] cmd = {'R', 'D', 'L', 'U'};
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];

        for(int r = 0; r < row; r++) {
            map[r] = br.readLine().trim().toCharArray();
        }
    }

    static void getRes() {
        int[][] zone = new int[row][col];
        int idx = 0;

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(zone[r][c] == 0) {
                    int nowR = r;
                    int nowC = c;
                    idx++;

                    while(zone[nowR][nowC] == 0) {
                        zone[nowR][nowC] = idx;

                        for(int dir = 0; dir < 4; dir++) {
                            if(map[nowR][nowC] == cmd[dir]) {
                                nowR += dR[dir];
                                nowC += dC[dir];

                                break;
                            }
                        }
                    }

                    if(zone[nowR][nowC] == idx) {
                        res++;
                    }
                }
            }
        }
    }
}
