import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int sec, height;
    static int row, col, blk;
    static int min, max;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        blk = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        sec = 987654321;
        min = 987654321;
        max = 0;

        for(int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                min = Math.min(min, map[r][c]);
                max = Math.max(max, map[r][c]);
            }
        }
    }

    static void getRes() {
        for(int i = max; i >= min; i--) {
            int dig = 0;
            int set = 0;

            for(int r = 0; r < row; r++) {
                for(int c = 0; c < col; c++) {
                    if(map[r][c] >= i) {
                        dig += map[r][c] - i;
                    } else {
                        set += i - map[r][c];
                    }
                }
            }
            
            int need = dig * 2 + set;
            
            if(dig + blk >= set && sec > need) {
                sec = need;
                height = i;
            }
        }

        sb.append(sec).append(" ").append(height);
    }
}
