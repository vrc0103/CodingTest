import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int cd, ck;
    static int[] card;
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.println(sb);
    }

    static void getRes() throws Exception {
        cd = Integer.parseInt(br.readLine().trim());
        card = new int[cd];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < cd; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(card);

        ck = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        while(ck-- > 0) {
            int num = Integer.parseInt(st.nextToken());

            if(Arrays.binarySearch(card, num) >= 0) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append(" ");
        }
    }
}
