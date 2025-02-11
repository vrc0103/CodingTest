import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int num, gap;
    static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getSeq();

        System.out.print(sb.toString());
    }

    static void getSeq() throws IOException {
        int sel;

        st = new StringTokenizer(br.readLine().trim());

        num = Integer.parseInt(st.nextToken());
        gap = Integer.parseInt(st.nextToken());

        for (int i = 0; i < num; i++) {
            list.add(i + 1);
        }

        sel = gap - 1;

        sb.append("<");
        while (!list.isEmpty()) {
            sb.append(list.remove(sel));

            // 다음 원소가 있을 때만 ", " 추가 및 다음 사람 선택
            if (list.size() > 0) {
                sb.append(", ");

                // remove 하면서 다음 원소로 이동한 상태이므로 -1
                sel = (sel + gap - 1) % list.size();
            }
        }
        sb.append(">\n");
    }
}
