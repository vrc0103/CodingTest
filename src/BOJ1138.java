import java.io.*;
import java.util.*;

public class BOJ1138 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int num;
    static int[] count;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.print(sb.toString());
    }

    static void setInfo() throws IOException {
        num = Integer.parseInt(br.readLine().trim());

        count = new int[num + 1];
        st = new StringTokenizer(br.readLine().trim());

        for (int i = 1; i <= num; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        LinkedList<Integer> order = new LinkedList<>();
        int loc;

        // 뒤에서부터(키 큰 사람부터) 탐색하며 해당 위치에 삽입
        // -> 작은 사람이 동일한 위치로 삽입될 시 큰 사람이 뒤로 밀리며 키에 따른 순서 유지됨
        for (int i = num; i > 0; i--) {
            loc = count[i];

            order.add(loc, i);
        }

        for (int i = 0; i < num; i++) {
            sb.append(order.get(i)).append(" ");
        }
        sb.append("\n");
    }
}