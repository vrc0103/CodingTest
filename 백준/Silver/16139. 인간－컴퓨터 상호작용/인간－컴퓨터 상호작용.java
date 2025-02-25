import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static String input;
    static int oper;
    static int[][] cnt;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        input = br.readLine().trim();
        cnt = new int[input.length() + 1][26];

        for(int i = 1; i <= input.length(); i++) {
            for(int j = 0; j < 26; j++) {
                cnt[i][j] = cnt[i - 1][j];
            }
            
            cnt[i][input.charAt(i - 1) - 'a']++;
        }

        oper = Integer.parseInt(br.readLine().trim());

        while(oper-- > 0) {
            int count = 0;

            st = new StringTokenizer(br.readLine().trim());
            int idx = st.nextToken().charAt(0) - 'a';
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            count = cnt[end + 1][idx] - cnt[start][idx];

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }
}
