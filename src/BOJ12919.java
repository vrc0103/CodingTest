import java.io.*;
import java.util.*;

public class BOJ12919 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static StringBuilder origin = new StringBuilder();
    static StringBuilder change = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 정보
        origin.append(br.readLine().trim());
        change.append(br.readLine().trim());

        // 풀이
        DFS(change);

        System.out.println(res);
    }

    static void DFS(StringBuilder sb) {
        // toString()도 String 객체를 생성하는 메서드라서 시간이 꽤 걸림
        // -> 최소한으로 호출하는 게 좋음
        if (sb.length() == origin.length() && sb.toString().equals(origin.toString())) {
            res = 1;

            return;
        }

        int len = sb.length();

        if (len == 0) {
            return;
        }

        // 마지막 문자가 A이면 삭제
        if (sb.charAt(len - 1) == 'A' && res == 0) {
            // deleteCharAt()는 in-place 메서드이므로 다시 A를 추가해야 함
            DFS(sb.deleteCharAt(len - 1));
            sb.append('A');
        }

        // 첫 문자가 B이면 B 삭제 후 reverse
        if (sb.charAt(0) == 'B' && res == 0) {
            // reverse()도 마찬가지로 in-place 메서드라서 다시 돌려놔야 함
            sb.deleteCharAt(0).reverse();
            DFS(sb);
            sb.append('B').reverse();
        }
    }
}
