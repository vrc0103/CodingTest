import java.io.*;
import java.util.*;

public class BOJ6588 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static boolean[] isPrime = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        findPrime();

        getRes();

        System.out.print(sb.toString());
    }

    static void findPrime() {
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= 1000000; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime[i] = false;

                    break;
                }
            }
        }
    }

    static void getRes() throws IOException {
        int input;
        boolean pos;

        while (true) {
            input = Integer.parseInt(br.readLine().trim());

            if (input == 0) {
                return;
            }

            pos = false;

            for (int i = 3; i <= input / 2; i++) {
                if (isPrime[i] && isPrime[input - i]) {
                    // String.format이 시간이랑 메모리 엄청 잡아먹음
                    // sb.append(String.format("%d = %d + %d\n", input, i, input - i));
                    sb.append(input).append(" = ").append(i).append(" + ").append(input - i).append("\n");
                    pos = true;

                    break;
                }
            }

            if (!pos) {
                sb.append("Goldbach's conjecture is wrong.\n");
            }
        }
    }
}
