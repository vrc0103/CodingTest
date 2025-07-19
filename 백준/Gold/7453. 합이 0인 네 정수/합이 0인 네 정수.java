import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static long res;
    static int size;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        size = Integer.parseInt(br.readLine().trim());

        arr = new int[size][4];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        int[] AB = new int[size * size];
        int[] CD = new int[size * size];
        int idx = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                AB[idx] = arr[i][0] + arr[j][1];
                CD[idx] = arr[i][2] + arr[j][3];
                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        int idx1 = 0;
        int idx2 = size * size - 1;

        while (idx1 < size * size && idx2 >= 0) {
            int val1 = AB[idx1];
            int val2 = CD[idx2];
            int sum = val1 + val2;

            if (sum < 0) {
                idx1++;
            } else if (sum > 0) {
                idx2--;
            } else {
                int cnt1 = 0;
                int cnt2 = 0;

                while (idx1 < size * size && val1 == AB[idx1]) {
                    idx1++;
                    cnt1++;
                }

                while (idx2 >= 0 && val2 == CD[idx2]) {
                    idx2--;
                    cnt2++;
                }

                res += (long) cnt1 * cnt2;
            }
        }
    }
}
