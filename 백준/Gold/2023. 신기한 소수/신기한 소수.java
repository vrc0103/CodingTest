import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int order;
    static List<Integer> prime;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        order = Integer.parseInt(br.readLine().trim());
        prime = new ArrayList<>();
        
        findPrime(0, 0);

        for(int i : prime) {
            sb.append(i).append("\n");
        }
    }

    static void findPrime(int idx, int now) {
        if(idx == order) {
            prime.add(now);

            return;
        }

        for(int i = 1; i < 10; i++) {
            int next = now * 10 + i;
            
            if(next == 1) {
                continue;
            }

            boolean isPrime = true;

            for(int j = 2; j * j <= next; j++) {
                if(next % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if(isPrime) {
                findPrime(idx + 1, next);
            }
        }
    }
}
