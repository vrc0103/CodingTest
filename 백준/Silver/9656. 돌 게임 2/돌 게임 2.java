import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static String res;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        int num = Integer.parseInt(br.readLine().trim()) % 6;

        if (num % 2 == 0) {
            res = "SK";
        } else {
            res = "CY";
        }
    }
}
