import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static ArrayList<Integer> stack = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        myStack();

        System.out.print(sb);
    }

    static void myStack() throws IOException {
        int len = Integer.parseInt(br.readLine());
        int num;
        String oper;

        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            oper = st.nextToken();

            if (oper.equals("push")) {
                num = Integer.parseInt(st.nextToken());
                stack.add(num);
            } else if (oper.equals("pop")) {
                if (stack.size() == 0) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(stack.remove(stack.size() - 1)).append("\n");
                }
            } else if (oper.equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (oper.equals("empty")) {
                sb.append(stack.size() == 0 ? 1 : 0).append("\n");
            } else if (oper.equals("top")) {
                sb.append(stack.size() == 0 ? -1 : stack.get(stack.size() - 1)).append("\n");
            }
        }
    }
}
