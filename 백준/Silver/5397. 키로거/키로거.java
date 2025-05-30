import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static Deque<Character> left, right;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            getRes();
        }

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        StringBuilder tcRes = new StringBuilder();

        left = new ArrayDeque<>();
        right = new ArrayDeque<>();

        String log = br.readLine().trim();
        int len = log.length();

        for (int i = 0; i < len; i++) {
            char ch = log.charAt(i);

            if (ch == '<') {
                if (!left.isEmpty()) {
                    right.push(left.pop());

                }
            } else if (ch == '>') {
                if (!right.isEmpty()) {
                    left.push(right.pop());

                }
            } else if (ch == '-') {
                if (!left.isEmpty()) {
                    left.pop();
                }
            } else {
                left.push(ch);
            }
        }

        while (!left.isEmpty()) {
            tcRes.append(left.pop());
        }

        tcRes.reverse();

        while (!right.isEmpty()) {
            tcRes.append(right.pop());
        }

        sb.append(tcRes).append("\n");
    }
}
