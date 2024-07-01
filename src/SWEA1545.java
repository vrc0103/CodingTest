/*
 * 정수 n을 입력받고
 * n부터 0까지 역순으로 출력
 */

import java.io.*;

public class SWEA1545 {
    public static void main(String[] args) throws IOException {
        int nInput;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        nInput = Integer.parseInt(bf.readLine());

        for (int i = 0; i <= nInput; i++) {
            System.out.print((nInput - i) + " ");
        }
    }
}
