import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int size, len, lim;
    static int max;
    static int[][] map;
    static int[][] value;

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
        max = 0;
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());
        lim = Integer.parseInt(st.nextToken());

        map = new int[size][size];

        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int col = size - len + 1;
        value = new int[size][col];

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < col; c++) {
                max = 0;
                calc(r, c, 0, 0, 0);
                value[r][c] = max;
            }
        }

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < col; c++) {
                for(int i = r; i < size; i++) {
                    int startC = i == r ? c + len : 0;

                    for(int j = startC; j < col; j++) {
                        res = Math.max(res, value[r][c] + value[i][j]);
                    }
                }
            }
        }
    }

    static void calc(int r, int c, int sum, int pow, int cnt) {
        if(sum > lim) {
            return;
        }
        if(cnt == len) {
            max = Math.max(max, pow);

            return;
        }

        calc(r, c + 1, sum + map[r][c], pow + map[r][c] * map[r][c], cnt + 1);
        
        calc(r, c + 1, sum, pow, cnt + 1);
    }
}
