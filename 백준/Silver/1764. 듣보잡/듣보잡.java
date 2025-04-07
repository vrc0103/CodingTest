import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int dt, bo;
    static HashSet<String> hset = new HashSet<>();
    static TreeSet<String> tset = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        dt = Integer.parseInt(st.nextToken());
        bo = Integer.parseInt(st.nextToken());

        while(dt-- > 0) {
            hset.add(br.readLine().trim());
        }

        while(bo-- > 0) {
            String dbj = br.readLine().trim();

            if(hset.contains(dbj)) {
                tset.add(dbj);
            }
        }

        sb.append(tset.size()).append("\n");
        while(tset.size() > 0) {
            sb.append(tset.pollFirst()).append("\n");
        }
    }
}
