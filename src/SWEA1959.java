/*
 * 첫 줄에 테스트 케이스 갯수 T
 * N 개의 숫자로 구성된 숫자열 Ai, M 개의 숫자로 구성된 숫자열 Bj
 * 둘째 줄에 N, M 입력
 * 셋째, 넷째 줄에 Ai, Bj 입력(자릿수는 띄어쓰기로 구분)
 */

import java.io.*;
import java.util.*;

public class SWEA1959 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nSum, nRes;
        int nN, nM;
        int[] nA, nB;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(bf.readLine().trim());
        for (int test = 0; test < nT; test++) {
            nRes = 0;

            st = new StringTokenizer(bf.readLine().trim());
            nN = Integer.parseInt(st.nextToken());
            nM = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine().trim()); // 숫자열 A를 배열에 저장
            nA = new int[st.countTokens()];
            for (int i = 0; i < nN; i++) {
                nA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(bf.readLine().trim()); // 숫자열 B를 배열에 저장
            nB = new int[st.countTokens()];
            for (int i = 0; i < nM; i++) {
                nB[i] = Integer.parseInt(st.nextToken());
            }

            if (nN > nM) { // nA가 더 긴 숫자열
                for (int i = 0; i < nN - nM + 1; i++) {
                    nSum = 0;
                    for (int j = 0; j < nM; j++) {
                        nSum += nA[i + j] * nB[j];
                    }
                    if (nSum > nRes) { // 최댓값 갱신
                        nRes = nSum;
                    }
                }
            } else {
                for (int i = 0; i < nM - nN + 1; i++) {
                    nSum = 0;
                    for (int j = 0; j < nN; j++) {
                        nSum += nB[i + j] * nA[j];
                    }
                    if (nSum > nRes) { // 최댓값 갱신
                        nRes = nSum;
                    }
                }
            }

            System.out.println("#" + (test + 1) + " " + nRes);
        }
    }
}
