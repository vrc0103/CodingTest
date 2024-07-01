import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.PriorityQueue;

public class SWEA2930_PQ {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int nTestCase;
    public static int nInstLen;
    public static int nReturn;
    public static PriorityQueue<Integer> maxHeap;

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nTestCase; tc++) {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            sb.append("#").append(tc);

            nInstLen = Integer.parseInt(br.readLine().trim());
            for (int inst = 0; inst < nInstLen; inst++) {
                st = new StringTokenizer(br.readLine().trim());
                if (Integer.parseInt(st.nextToken()) == 1) {
                    maxHeap.add(Integer.parseInt(st.nextToken()));
                } else {
                    if (maxHeap.isEmpty()) {
                        nReturn = -1;
                    } else {
                        nReturn = maxHeap.poll();
                    }
                    sb.append(" ").append(nReturn);
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
