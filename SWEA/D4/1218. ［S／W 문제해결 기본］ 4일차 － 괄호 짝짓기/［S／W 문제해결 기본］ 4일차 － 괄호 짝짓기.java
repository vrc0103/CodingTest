import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int len;
    static String input;
    static Stack<Character> stack;
    static char[] chs = {'(', '{', '[', '<', ')', '}', ']', '>'};

    public static void main(String[] args) throws Exception {
        int idx;
        char ch;
        boolean pos;

        for(int tc = 1; tc <= 10; tc++) {
            pos = true;

            len = Integer.parseInt(br.readLine().trim());
            input = br.readLine().trim();

            stack = new Stack<Character>();

            for(int i = 0; i < len; i++) {
                ch = input.charAt(i);

                for(idx = 0; idx < 8; idx++) {
                    if(ch == chs[idx]) {
                        break;
                    }
                }
                
                if(idx < 4) {
                    stack.push(ch);
                }
                else if(stack.pop() != chs[idx - 4]) {
                    pos = false;

                    break;
                }
            }

            if(!stack.isEmpty()) {
                pos = false;
            }

            sb.append("#").append(tc).append(" ").append(pos ? 1 : 0).append("\n");
        }

        System.out.print(sb);
    }
}
