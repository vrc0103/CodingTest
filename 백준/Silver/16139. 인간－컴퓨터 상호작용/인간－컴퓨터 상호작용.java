import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static String input;
    static int oper;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        input = br.readLine().trim();

        oper = Integer.parseInt(br.readLine().trim());

        while(oper-- > 0) {
            int cnt = 0;

            st = new StringTokenizer(br.readLine().trim());
            char ch = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for(int i = start; i <= end; i++) {
                if(input.charAt(i) == ch) {
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }
}
