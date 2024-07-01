/*
 * N을 입력받아 2의 N제곱 출력
 */

import java.io.*;
import java.util.*;

public class SWEA2019 {
    public static void main(String[] args) throws IOException {
        int nSqr;
        int res = 1;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        nSqr = Integer.parseInt(st.nextToken());

        for (int i = 0; i < nSqr; i++) {
            System.out.print(res + " ");
            res *= 2;
        }
        System.out.print(res);
    }
}
