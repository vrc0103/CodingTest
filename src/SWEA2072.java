/*
 * 첫 줄에 케이스 갯수 T
 * 정수 10개 입력받아 홀수만 더하기
 */

import java.io.*;
import java.util.*;

public class SWEA2072 {
    public static void main(String[] args) throws IOException {
        int T;
        int nSum;
        int nTmp;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(bf.readLine());

        for (int i = 0; i < T; i++) {
            nSum = 0;
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < 10; j++) {
                nTmp = Integer.parseInt(st.nextToken());

                if (nTmp % 2 == 1)
                    nSum += nTmp;
            }

            System.out.println("#" + (i + 1) + " " + nSum);
        }
    }
}
