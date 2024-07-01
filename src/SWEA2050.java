/*
 * 영어 문자열을 입력받아 숫자로 변환 (A = 1 , Z = 26)
 */

import java.io.*;

public class SWEA2050 {
    public static void main(String[] args) throws IOException {
        int[] convInt;
        String sInput;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sInput = bf.readLine().toUpperCase(); // 소문자 입력 시 대문자로 변환
        convInt = new int[sInput.length()];

        for (int i = 0; i < sInput.length(); i++) {
            convInt[i] = sInput.charAt(i) - 64;
            System.out.print(convInt[i] + " ");
        }
    }
}
