import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static long num;
    static boolean[] win = { false, true, false, true, true, true, true, false };

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(win[(int)num] ? "SK" : "CY");
    }

    static void getRes() throws Exception {
        num = Long.parseLong(br.readLine().trim());

        num = num % 7;
    }
}
