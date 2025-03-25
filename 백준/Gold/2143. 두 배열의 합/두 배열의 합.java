import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static long res;
    static long target;
    static int[] size;
    static int[][] arr;
    static long[][][] sum;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        target = Long.parseLong(br.readLine().trim());
        size = new int[2];
        arr = new int[2][];
        sum = new long[2][][];

        for(int i = 0; i < 2; i++) {
            size[i] = Integer.parseInt(br.readLine().trim());
            arr[i] = new int[size[i] + 1];
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 1; j <= size[i]; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        HashMap<Long, Long>[] hmap = new HashMap[2];

        for(int i = 0; i < 2; i++) {
            sum[i] = new long[size[i] + 1][size[i] + 1];
            hmap[i] = new HashMap<>();

            // 부분 수열의 누적합
            for(int r = 1; r <= size[i]; r++) {
                for(int c = r; c <= size[i]; c++) {
                    if(r == 1) {
                        sum[i][r][c] = sum[i][r][c - 1] + arr[i][c];
                    } else {
                        sum[i][r][c] = sum[i][r - 1][c] - arr[i][r - 1];
                    }

                    hmap[i].put(sum[i][r][c], hmap[i].getOrDefault(sum[i][r][c], 0L) + 1);
                }
            }
        }

        // 경우의 수 찾기
        for(long i : hmap[0].keySet()) {
            long need = target - i;
            
            res += hmap[0].get(i) * hmap[1].getOrDefault(need, 0L);
        }
    }
}
