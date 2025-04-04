import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size;
    static boolean[][] map;

    static int[] dR = {-1, -1, -1};
    static int[] dC = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.print(res);
    }

    static void getRes() throws Exception {
        size = Integer.parseInt(br.readLine().trim());

        map = new boolean[size][size];

        dfs(0);
    }

    static void dfs(int row) {
        if(row == size) {
            res++;
            return;
        }

        for(int col = 0; col < size; col++) {
            boolean flag = true;

            loop: for(int idx = 0; idx < 3; idx++) {
                int nextR = row;
                int nextC = col;

                while(true) {
                    nextR += dR[idx];
                    nextC += dC[idx];

                    if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                        break;
                    }

                    if(map[nextR][nextC]) {
                        flag = false;
                        break loop;
                    }
                }

            }
            
            if(flag) {
                map[row][col] = true;
                dfs(row + 1);
                map[row][col] = false;
            }
        }
    }
}