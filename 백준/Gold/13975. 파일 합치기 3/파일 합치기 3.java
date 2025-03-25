import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static long res;
    static int len;
    static PriorityQueue<Long> pq;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = 0;
        len = Integer.parseInt(br.readLine().trim());
        pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine().trim());
        while(len-- > 0) {
            pq.add(Long.parseLong(st.nextToken()));
        }
    }

    static void getRes() {
        while(pq.size() > 1) {
            long file1 = pq.remove();
            long file2 = pq.remove();
            long sum = file1 + file2;

            pq.add(sum);
            res += sum;
        }

        sb.append(res).append("\n");
    }
}
