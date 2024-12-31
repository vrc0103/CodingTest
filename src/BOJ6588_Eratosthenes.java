import java.io.*;
import java.util.*;

public class BOJ6588_Eratosthenes {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int MAX = 1_000_000;
    static boolean[] isPrime = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        Eratosthenes();

        getRes();

        System.out.print(sb.toString());
    }

    static void Eratosthenes() {
        /*
         * 에라토스테네스의 체
         * 
         * 가장 작은 소수 2부터 스스로를 제외한 배수 제거
         * 다음 숫자로 이동 후 반복
         * -> 결과적으로 소수만 남음
         * 
         * 탐색할 최대 숫자에 루트를 씌운 값까지만 확인하면 됨
         * : 해당 숫자 이후부터는 소수의 2, 3, 5, ... 배수이므로 앞에서 삭제되었음
         */

        Arrays.fill(isPrime, true);

        for (int i = 2; i <= Math.sqrt(MAX); i++) {
            // 해당 숫자가 소수이면
            if (isPrime[i]) {
                // 자신을 제외한 배수 제거
                for (int j = 2; i * j <= MAX; j++) {
                    isPrime[i * j] = false;
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
