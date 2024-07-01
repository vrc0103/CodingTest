/*
 * 첫 줄에 케이스 갯수 T
 * 1부터 N까지 홀수는 더하고 짝수는 뺀 결과값 출력
 */

import java.io.*;

public class SWEA1986 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nRes;
        int nInput;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine());

        for (int i = 0; i < nT; i++) {
            nRes = 0;
            nInput = Integer.parseInt(bf.readLine());

            for (int j = 1; j <= nInput; j++) {
                if (j % 2 == 1) // 홀수는 더하고
                    nRes += j;
                else // 짝수는 뺌
                    nRes -= j;
            }

            System.out.println("#" + (i + 1) + " " + nRes);
        }
    }
}
