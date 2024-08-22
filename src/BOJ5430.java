import java.io.*;
import java.util.*;

public class BOJ5430 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static boolean reverse;
    static int size;
    static int len;
    static String oper;
    static Deque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            getRes();
        }

        System.out.print(sb.toString());
    }

    // 데크 메소드 : https://soft.plusblog.co.kr/24
    static void getRes() throws IOException {
        oper = br.readLine().trim();
        size = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim(), "[,]");

        for (int i = 0; i < size; i++) {
            dq.add(Integer.parseInt(st.nextToken()));
        }

        len = oper.length();
        reverse = false;
        for (int i = 0; i < len; i++) {
            if (oper.charAt(i) == 'R') {
                reverse = reverse ? false : true;
            } else {
                if (dq.isEmpty()) {
                    sb.append("error\n");
                    return;
                }

                if (reverse) {
                    dq.removeLast();
                } else {
                    dq.removeFirst();
                }
            }
        }

        if (dq.isEmpty()) {
            sb.append("[]\n");
        } else {
            int count = dq.size();

            sb.append("[");

            if (reverse) {
                for (int i = 0; i < count - 1; i++) {
                    sb.append(dq.removeLast()).append(",");
                }
                sb.append(dq.remove()).append("]\n");
            }

            else {
                for (int i = 0; i < count - 1; i++) {
                    sb.append(dq.removeFirst()).append(",");
                }
                sb.append(dq.remove()).append("]\n");
            }
        }
    }
}
