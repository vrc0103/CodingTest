/*
 * A사 : 1L당 P원
 * B사 : R리터 이하 = 기본 요금 Q원 , R리터 초과 = 1L당 S원
 * 한 달간 사용하는 물 W 리터
 * 순서대로 P Q R S W 입력 , 저렴한 회사의 최종 요금 출력
 */

import java.io.*;
import java.util.*;

public class SWEA1284 {
    public static int payWater(int nP, int nQ, int nR, int nS, int nW) {
        int nPayA;
        int nPayB;

        nPayA = nP * nW; // P원 * W리터
        if (nW > nR) // 사용량 W가 R 초과
            nPayB = nQ + (nW - nR) * nS; // 기본요금 Q원 + 초과분(W-R) * R원
        else
            nPayB = nQ;

        if (nPayA > nPayB) // B가 싸면 B 반환
            return nPayB;
        else // A가 싸거나 동일하면 A 반환
            return nPayA;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int nP, nQ, nR, nS, nW;
        int waterFee;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(bf.readLine());
        for (int test = 0; test < nT; test++) {
            st = new StringTokenizer(bf.readLine());
            nP = Integer.parseInt(st.nextToken());
            nQ = Integer.parseInt(st.nextToken());
            nR = Integer.parseInt(st.nextToken());
            nS = Integer.parseInt(st.nextToken());
            nW = Integer.parseInt(st.nextToken());

            waterFee = payWater(nP, nQ, nR, nS, nW);
            System.out.println("#" + (test + 1) + " " + waterFee);
        }
    }
}
