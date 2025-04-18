import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        int input = Integer.parseInt(br.readLine().trim());

        if(input == 1 || input == 3) {
            res = -1;
            return;
        }

        int five = input / 5;
        input %= 5;
        
        if((input % 2) == 1) {
            five--;
            input += 5;
        }
        
        int two = input / 2;

        res = five + two;
    }
}
