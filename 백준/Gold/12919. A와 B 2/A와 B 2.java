import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static String origin, change;

    public static void main(String[] args) throws Exception {
        // 정보
        origin = br.readLine().trim();
        change = br.readLine().trim();

        // 풀이
        DFS(change);

        System.out.println(res);
    }

    static void DFS(String sb) {
        if (sb.toString().equals(origin.toString())) {
            res = 1;

            return;
        }

        int len = sb.length();

        if (len == 0) {
            return;
        }

        // 마지막 문자가 A이면 삭제
        if (sb.charAt(len - 1) == 'A' && res == 0) {
            StringBuilder tmp = new StringBuilder();
            tmp.append(sb);

            DFS(tmp.deleteCharAt(len - 1).toString());
            tmp.append('A');
        }

        // 첫 문자가 B이면 B 삭제 후 reverse
        if (sb.charAt(0) == 'B' && res == 0) {
            StringBuilder tmp = new StringBuilder();
            tmp.append(sb);

            tmp.deleteCharAt(0);
            DFS(tmp.reverse().toString());
            tmp.append('B');
        }
    }
}
