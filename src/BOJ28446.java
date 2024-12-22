import java.io.*;
import java.util.*;

public class BOJ28446 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Ball implements Comparable<Ball> {
        int num;
        int weight;

        public Ball(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Ball o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        int count;
        int operand;
        int num, weight;
        ArrayList<Ball> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        count = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine().trim());
            operand = Integer.parseInt(st.nextToken());

            // insert
            if (operand == 1) {
                num = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());

                list.add(new Ball(num, weight));
            }
            // select
            else if (operand == 2) {
                weight = Integer.parseInt(st.nextToken());

                queue.add(weight);
            }
        }

        // 무게를 기준으로 정렬 후 이분 탐색
        int left, right, mid;

        Collections.sort(list);

        while (!queue.isEmpty()) {
            weight = queue.remove();
            left = 0;
            right = list.size() - 1;
            mid = (left + right) / 2;

            while (right >= left) {
                if (list.get(mid).weight == weight) {
                    break;
                } else if (list.get(mid).weight < weight) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

                mid = (left + right) / 2;
            }

            sb.append(list.get(mid).num).append("\n");
        }
    }
}