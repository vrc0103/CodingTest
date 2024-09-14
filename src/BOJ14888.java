import java.io.*;
import java.util.*;

public class BOJ14888 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int num;
    static int min, max;
    static int[] input;
    static int[] oper;

    public static void main(String[] args) throws IOException {
        getInfo();

        getMinMax(1, input[0]);

        System.out.printf("%d\n%d\n", max, min);
    }

    static void getInfo() throws IOException {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        num = Integer.parseInt(br.readLine().trim());

        input = new int[num];
        oper = new int[4];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getMinMax(int cnt, int res) {
        if (cnt == num) {
            min = Math.min(min, res);
            max = Math.max(max, res);

            return;
        }

        if (oper[0] > 0) {
            oper[0]--;
            getMinMax(cnt + 1, res + input[cnt]);
            oper[0]++;
        }

        if (oper[1] > 0) {
            oper[1]--;
            getMinMax(cnt + 1, res - input[cnt]);
            oper[1]++;
        }

        if (oper[2] > 0) {
            oper[2]--;
            getMinMax(cnt + 1, res * input[cnt]);
            oper[2]++;
        }

        if (oper[3] > 0) {
            oper[3]--;
            getMinMax(cnt + 1, res / input[cnt]);
            oper[3]++;
        }
    }
}
