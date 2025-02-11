import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        count();
    }

    static void count() throws IOException {
        String input = br.readLine().trim();
        int len = input.length();
        char now, last;
        int count = 0;
        Stack<Character> stack = new Stack<>();

        // 시작은 무조건 여는 괄호
        stack.push(input.charAt(0));

        for (int i = 1; i < len; i++) {
            now = input.charAt(i);
            last = input.charAt(i - 1);

            // 여는 괄호 스택에 추가
            if (now == '(') {
                stack.push(now);
            }

            // 닫는 괄호인데 바로 앞이 여는 괄호 -> 레이저
            else if (last == '(') {
                stack.pop();
                count += stack.size();
            }

            // 닫는 괄호인데 바로 앞이 닫는 괄호 -> 막대
            else {
                stack.pop();
                count += 1;
            }
        }

        System.out.println(count);
    }
}
