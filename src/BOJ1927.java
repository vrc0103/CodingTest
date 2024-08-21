import java.io.*;
import java.util.*;

public class BOJ1927 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int len = Integer.parseInt(br.readLine().trim());
        long input;
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < len; i++) {
            input = Long.parseLong(br.readLine().trim());

            if (input == 0) {
                if (!pq.isEmpty()) {
                    sb.append(pq.poll()).append("\n");
                } else {
                    sb.append("0\n");
                }
            } else {
                pq.add(input);
            }
        }

        System.out.print(sb);
    }
}
