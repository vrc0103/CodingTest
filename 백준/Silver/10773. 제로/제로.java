import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println(getSum());
    }

    static long getSum() throws IOException {
        int len = Integer.parseInt(br.readLine().trim());
        int num;
        long res = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            num = Integer.parseInt(br.readLine().trim());

            if (num > 0) {
                stack.push(num);
            } else {
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}
