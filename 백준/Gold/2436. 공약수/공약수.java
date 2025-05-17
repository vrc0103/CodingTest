import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int max, min;
    static int mid;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.println(sb);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        max = Integer.parseInt(st.nextToken());
        min = Integer.parseInt(st.nextToken());

        int num = min / max;
        
        int mid = (int) Math.sqrt(num);
        for(int i = mid; i > 0; i--) {
            if(num % i == 0) {
                int a = num / i;
                int b = i;

                while(b > 0) {
                    int tmp = a;
                    a = b;
                    b = tmp % b;
                }

                if(a == 1) {
                    sb.append(i * max).append(" ").append(num / i * max);
                    break;
                }
            }
        }
    }
}

