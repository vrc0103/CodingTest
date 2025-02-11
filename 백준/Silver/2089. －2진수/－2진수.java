import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int num;

    public static void main(String[] args) throws Exception {
        getRes();

        System.out.println(sb.reverse());
    }

    static void getRes() throws Exception {

        num = Integer.parseInt(br.readLine().trim());

        if (num == 0) {
            sb.append("0");
        } else {
            while (num != 1) {
                sb.append(Math.abs(num % -2));
                num = (int) (Math.ceil((double) num / -2));
            }

            sb.append(num);
        }
    }
}
