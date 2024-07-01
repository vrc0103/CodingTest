/*
 * 첫 줄에 일수 N
 * 다음 줄에 일자별 가격
 */

import java.io.*;
import java.util.*;

public class SWEA1859 {
    public static long getMoney(int[] nPriceArr) {
        int nDay = 0;
        int nStart;
        int nMax;
        long nSum = 0;

        while (true) {
            nStart = nDay;
            nMax = 0;

            for (int i = nStart; i < nPriceArr.length; i++) {
                if (nMax <= nPriceArr[i]) { // 물품 가격이 가장 비싼 날짜 찾기, 최댓값 여러개면 가장 나중 날짜로
                    nMax = nPriceArr[i];
                    nDay = i;
                }
            }

            for (int i = nStart; i < nDay; i++) { // 가장 비싼 날 모든 매물 처분
                nSum += nPriceArr[nDay] - nPriceArr[i];
            }

            if (nDay < nPriceArr.length - 1) { // 판매 다음날부터 사재기 시작
                nDay++;
            } else { // 마지막날이면 무한루프 종료
                break;
            }
        }
        return nSum;
    }

    public static void main(String[] args) throws IOException {
        // Max 값을 먼저 찾고 해당 일자에 정산
        // 이후 날짜들 중에서 Max값을 찾고 정산 반복
        int nT, nN;
        int[] nPriceArr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nN = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());

            nPriceArr = new int[nN];
            for (int i = 0; i < nN; i++) {
                nPriceArr[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println("#" + test + " " + getMoney(nPriceArr));
        }
    }
}
