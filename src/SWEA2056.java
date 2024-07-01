/*
 * 첫 줄에 케이스 갯수 T
 * 각 줄에 날짜 형식 YYYYMMDD 입력
 * -> YYYY/MM/DD 형식으로 출력
 * 날짜 형식에 맞지 않으면(1 ~ 12월 이외, 각 월별 최대 일수 초과) -1 출력
 */

import java.io.*;

public class SWEA2056 {
    public static int YMDCheck(String YMD) {
        int nM = Integer.parseInt(YMD.substring(4, 6));
        int nD = Integer.parseInt(YMD.substring(6));

        if (nM >= 1 && nM <= 12) { // MM 조건이 맞는 경우
            if ((nM == 1 || nM == 3 || nM == 5 || nM == 7 || nM == 8 || nM == 10 || nM == 12) && (nD >= 1 && nD <= 31))
                return 1;
            else if ((nM == 4 || nM == 6 || nM == 9 || nM == 11) && (nD >= 1 && nD <= 30))
                return 1;
            else if (nM == 2 && (nD >= 1 && nD <= 28))
                return 1;
            else
                return 0;
        } else
            return 0;

    }

    public static void main(String[] args) throws IOException {
        int nT;
        String YMD;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine()); // 케이스 갯수
        for (int i = 0; i < nT; i++) {
            YMD = bf.readLine();
            if (YMDCheck(YMD) == 0) {
                System.out.println("#" + (i + 1) + " " + "-1");
                continue;
            }
            System.out.println(
                    ("#" + (i + 1) + " " + YMD.substring(0, 4)) + "/" + YMD.substring(4, 6) + "/" + YMD.substring(6));
        }
    }
}
