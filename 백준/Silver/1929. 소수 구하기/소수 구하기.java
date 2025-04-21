import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int M, N;
    static boolean[] notPrime;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        notPrime = new boolean[N + 1];
        notPrime[0] = true;
        notPrime[1] = true;
        
        for(int i = 2; i <= Math.sqrt(N); i++) {
            if(!notPrime[i]) {
                for(int j = i * 2; j <= N; j += i) {
                    notPrime[j] = true;
                }
            }
        }

        for(int i = M; i <= N; i++) {
            if(!notPrime[i]) {
                sb.append(i).append("\n");
            }
        }
    }
}
