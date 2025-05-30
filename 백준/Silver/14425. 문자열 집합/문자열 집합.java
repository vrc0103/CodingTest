import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int res = 0;
        HashSet<String> set = new HashSet<>();

        int whole = Integer.parseInt(st.nextToken());
        int find = Integer.parseInt(st.nextToken());

        for(int i = 0; i < whole; i++) {
            set.add(br.readLine().trim());
        }

        for(int i = 0; i < find; i++) {
            String target = br.readLine().trim();

            if(set.contains(target)) {
                res++;
            }
        }

        System.out.println(res);
    }
}
