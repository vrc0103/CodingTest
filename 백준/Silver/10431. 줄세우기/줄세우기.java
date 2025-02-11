import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int num;
    static LinkedList<Integer> height;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            getRes();
        }

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        int count = 0;
        int idx, next;

        height = new LinkedList<>();
        st = new StringTokenizer(br.readLine().trim());

        num = Integer.parseInt(st.nextToken()); // tc 번호
        height.add(Integer.parseInt(st.nextToken())); // 첫 번째

        for (int i = 1; i < 20; i++) {
            next = Integer.parseInt(st.nextToken());
            idx = 0;

            for (int j = height.size() - 1; j >= 0; j--) {
                if (height.get(j) > next) {
                    count++;
                } else {
                    idx = j + 1;
                    break;
                }
            }

            height.add(idx, next);
        }

        sb.append(String.format("%d %d\n", num, count));
    }
}
