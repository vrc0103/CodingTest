import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        int num = Integer.parseInt(br.readLine().trim());
        Integer[] rope = new Integer[num];

        for (int i = 0; i < num; i++) {
            rope[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(rope, Collections.reverseOrder());

        for (int i = 0; i < num; i++) {
            res = Math.max(res, rope[i] * (i + 1));
        }
    }
}
