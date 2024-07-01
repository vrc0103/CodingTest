/*
 * 첫 줄에 케이스 갯수 T
 * 각 케이스에 정수 10개 입력받아 평균 구하기(소숫점 첫째 자리에서 반올림)
 */

import java.io.*;
import java.util.*;

public class SWEA2071 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nMean;
        int nRound;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(bf.readLine()); // 첫 줄 정수화 해서 저장
        for (int i = 0; i < nT; i++) {
            nMean = 0;
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < 10; j++)
                nMean += Integer.parseInt(st.nextToken());

            if (nMean % 10 >= 5) // 0.5 이상이면 반올림
                nRound = 1;
            else
                nRound = 0;

            nMean = nMean / 10 + nRound;
            System.out.println("#" + (i + 1) + " " + nMean);
        }
    }
}
