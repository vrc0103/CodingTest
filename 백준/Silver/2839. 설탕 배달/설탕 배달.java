import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res;
    static int input;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int num1, num2;
        int rest;
        boolean pos = false;

        input = Integer.parseInt(br.readLine().trim());
        num1 = input / 5;

        // 5kg이 최대일 때부터 1씩 줄이며 남은 설탕이 3으로 나누어 떨어지는지 확인
        for (int i = num1; i >= 0; i--) {
            rest = input - 5 * i;

            if (rest % 3 == 0) {
                num2 = rest / 3;

                res = i + num2;
                pos = true;
                break;
            }
        }

        if (!pos) {
            res = -1;
        }
    }
}
