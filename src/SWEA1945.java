/*
 * 첫 줄에 케이스 갯수 T
 * 2, 3, 5, 7, 11로 소인수분해 해서 각 소수의 지수 출력
 */

import java.io.*;

public class SWEA1945 {
    public static void primeNum(int nNum) {
        int num2 = 0;
        int num3 = 0;
        int num5 = 0;
        int num7 = 0;
        int num11 = 0;

        while (true) {
            if (nNum % 2 == 0) {
                nNum /= 2;
                num2++;
            }
            if (nNum % 3 == 0) {
                nNum /= 3;
                num3++;
            }
            if (nNum % 5 == 0) {
                nNum /= 5;
                num5++;
            }
            if (nNum % 7 == 0) {
                nNum /= 7;
                num7++;
            }
            if (nNum % 11 == 0) {
                nNum /= 11;
                num11++;
            }
            if (nNum == 1)
                break;
        }

        System.out.println(num2 + " " + num3 + " " + num5 + " " + num7 + " " + num11);
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int nInput;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine());

        for (int i = 0; i < nT; i++) {
            System.out.print("#" + (i + 1) + " ");
            nInput = Integer.parseInt(bf.readLine());
            primeNum(nInput);
        }
    }
}
