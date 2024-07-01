/*
 * 첫 줄에 테스트케이스 번호
 * 이후부터 N M 입력 -> N의 M제곱
 * 결과도 int 형식 범위
 */

import java.util.Scanner;

public class SWEA1217 {
    public static int Recrs(int nN, int nM) {
        if (nM == 0) {
            return 1;
        }
        return nN * Recrs(nN, nM - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nN, nM;
        int nTestCase;
        int nLen, nRes;
        String sInput;
        String sTmp = "";

        for (int tc = 1; tc <= 10; tc++) {
            nN = nM = 1;
            nLen = 1;

            nTestCase = Integer.parseInt(sc.nextLine());
            sInput = sc.nextLine();

            for (int i = 0; i < sInput.length(); i++) {
                if (sInput.charAt(i) == ' ') { // 첫 띄어쓰기 = nN
                    for (int j = 0; j < i; j++) {
                        sTmp += sInput.charAt(j);
                    }
                    nN = Integer.parseInt(sTmp);
                    sTmp = "";
                    nLen = i; // 띄어쓰기 위치 체크
                }
                if (i == sInput.length() - 1) { // 문자열 끝 = nM
                    for (int j = nLen + 1; j < sInput.length(); j++) {
                        sTmp += sInput.charAt(j);
                    }
                    nM = Integer.parseInt(sTmp);
                    sTmp = "";
                }
            }

            nRes = Recrs(nN, nM);

            System.out.println("#" + nTestCase + " " + nRes);
            sc.close();
        }
    }
}
