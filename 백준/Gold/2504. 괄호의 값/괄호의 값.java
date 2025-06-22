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
        int calc = 1;
        String input = br.readLine().trim();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char now = input.charAt(i);

            if (now == '(') {
                calc *= 2;
                stack.push(now);
            } else if (now == '[') {
                calc *= 3;
                stack.push(now);
            } else if (now == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    res = 0;
                    return;
                }

                // 열고 바로 닫는 경우에만 결과에 합산, 이외의 경우는 그냥 숫자 크기만 원복
                if (input.charAt(i - 1) == '(') {
                    res += calc;
                }

                calc /= 2;
                stack.pop();
            } else {
                if (stack.isEmpty() || stack.peek() != '[') {
                    res = 0;
                    return;
                }

                // 열고 바로 닫는 경우에만 결과에 합산, 이외의 경우는 그냥 숫자 크기만 원복
                if (input.charAt(i - 1) == '[') {
                    res += calc;
                }

                calc /= 3;
                stack.pop();
            }
        }

        if (!stack.isEmpty()) {
            res = 0;
        }
    }
}
