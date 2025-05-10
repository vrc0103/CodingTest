import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());

        int num = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());
        
        Map<String, String> map = new HashMap<>();

        for(int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            String address = st.nextToken();
            String password = st.nextToken();

            map.put(address, password);
        }

        for(int i = 0; i < cnt; i++) {
            String address = br.readLine().trim();

            sb.append(map.get(address)).append("\n");
        }
    }
}

