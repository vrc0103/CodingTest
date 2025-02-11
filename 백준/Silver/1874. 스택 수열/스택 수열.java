import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.out.print(judgeStack());
    }

    static String judgeStack() throws IOException {
        int len = Integer.parseInt(br.readLine().trim());
        int input;
        int num = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            input = Integer.parseInt(br.readLine().trim());

            if (num < input) {
                while (num < input) {
                    num++;
                    stack.push(num);
                    sb.append("+\n");
                }

                stack.pop();
                sb.append("-\n");
            } else {
                if (stack.isEmpty() || stack.peek() < input) {
                    return "NO\n";
                }
                while (!stack.isEmpty() && stack.peek() >= input) {
                    stack.pop();
                    sb.append("-\n");
                }
            }
        }

        return sb.toString();
    }
}
