//정수 N을 입력받아 모든 약수를 오름차순으로 출력

import java.util.*;
import java.io.*;

public class SWEA1933 {
    public static void main(String[] args) throws IOException {
        int N;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            if (N % i == 0)
                System.out.print(i + " ");
        }
    }
}
