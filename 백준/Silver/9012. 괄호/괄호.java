import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            judge();
        }

        System.out.print(sb);
    }

    static void judge() throws IOException {
        int len;
        char c;
        String input;

        input = br.readLine().trim();
        len = input.length();
        stack.clear();

        for (int i = 0; i < len; i++) {
            c = input.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (stack.isEmpty()) {
                sb.append("NO\n");
                return;
            } else {
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            sb.append("YES\n");
        } else {
            sb.append("NO\n");
        }
    }
}
