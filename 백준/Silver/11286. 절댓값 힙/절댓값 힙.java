import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static class Abs implements Comparable<Abs> {
        int num;
        int abs;

        public Abs(int num) {
            this.num = num;
            this.abs = Math.abs(num);
        }

        @Override
        public int compareTo(Abs o) {
            // 절댓값이 다르면 절댓값으로 비교
            if (this.abs != o.abs) {
                return this.abs - o.abs;
            }

            // 절댓값이 같으면 원래 숫자로 비교
            else {
                return this.num - o.num;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int len = Integer.parseInt(br.readLine().trim());
        int input;
        PriorityQueue<Abs> pq = new PriorityQueue<>();

        for (int i = 0; i < len; i++) {
            input = Integer.parseInt(br.readLine().trim());

            if (input == 0) {
                if (!pq.isEmpty()) {
                    sb.append(pq.poll().num).append("\n");
                } else {
                    sb.append("0\n");
                }
            } else {
                pq.add(new Abs(input));
            }
        }

        System.out.print(sb);
    }
}
