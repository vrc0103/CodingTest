import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int size;
    static int[][] map;
    static long[][] cnt;

    static int[] dR = {0, 1};
    static int[] dC = {1, 0};

    public static void main(String[] args) throws Exception {
        // 정보
        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];
        cnt = new long[size][size];

        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 풀이
        int len;

        cnt[0][0] = 1;
        
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                len = map[r][c];

                if(len == 0) {
                    continue;
                }

                if(r + len < size) {
                    cnt[r + len][c] += cnt[r][c];
                }

                if(c + len < size) {
                    cnt[r][c + len] += cnt[r][c];
                }
            }
        }

        System.out.println(cnt[size - 1][size - 1]);
    }
}
