import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int len;
    static HashMap<Integer, Integer> hmap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        while(len-- > 0) {
            int num = Integer.parseInt(st.nextToken());

            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }

        len = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        while (len-- > 0) {
            int num = Integer.parseInt(st.nextToken());

            sb.append(hmap.getOrDefault(num, 0)).append(" ");
        }
    }
}
