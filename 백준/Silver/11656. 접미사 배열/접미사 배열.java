import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        int len = input.length();
        String[] postfix = new String[len];

        for(int i = 0; i < len; i++) {
            postfix[i] = input.substring(i, len);
        }

        Arrays.sort(postfix);

        for(String s : postfix) {
            System.out.println(s);
        }
    }
}
