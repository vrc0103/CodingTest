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
        int target = Integer.parseInt(br.readLine().trim());

        while (target > 0) {
            if (target % 2 == 1) {
                res++;
            }

            target /= 2;
        }
    }
}
