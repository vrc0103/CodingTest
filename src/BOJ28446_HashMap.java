import java.io.*;
import java.util.*;

public class BOJ28446_HashMap {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    // 해시맵 : 키-값 쌍
    // 키 연산 시 해시를 적용 -> 탐색 빠름, 정렬 필요 X
    static HashMap<Integer, Integer> ball = new HashMap<>();

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.print(sb.toString());
    }

    static void getRes() throws IOException {
        int count;
        int operand;
        int num, weight;

        count = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine().trim());
            operand = Integer.parseInt(st.nextToken());

            // insert
            if (operand == 1) {
                num = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());

                // 해시맵의 삽입 메소드 : put
                // 무게를 기준으로 탐색 -> 무게를 키로 지정
                ball.put(weight, num);
            }
            // select
            else if (operand == 2) {
                weight = Integer.parseInt(st.nextToken());

                sb.append(ball.get(weight)).append("\n");
            }
        }
    }
}
