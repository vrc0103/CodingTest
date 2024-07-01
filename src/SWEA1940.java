/*
 * 명령 수 N
 * 유지(0), 가속(1), 감속(2) , 매 초마다 계산
 */

import java.io.*;
import java.util.*;

public class SWEA1940 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nN, nCmd, nAcc;
        int nRes;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nAcc = 0;
            nRes = 0;

            nN = Integer.parseInt(br.readLine().trim());
            for (int cmd = 0; cmd < nN; cmd++) {
                st = new StringTokenizer(br.readLine().trim());
                nCmd = Integer.parseInt(st.nextToken());
                switch (nCmd) {
                    case 0:
                        break;
                    case 1: {
                        nAcc += Integer.parseInt(st.nextToken());
                        break;
                    }
                    case 2: {
                        nAcc -= Integer.parseInt(st.nextToken());
                        if (nAcc < 0)
                            nAcc = 0;
                        break;
                    }
                }
                nRes += nAcc;
            }

            System.out.println("#" + test + " " + nRes);
        }
    }
}
