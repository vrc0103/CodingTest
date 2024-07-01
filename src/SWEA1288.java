//마지막으로 센 양의 숫자를 출력

import java.io.*;

public class SWEA1288 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nInput;
        int nCount; // 양 센 숫자
        int nTmp; // Input의 n배수
        int nSum; // 0~9 모두 셌는지 확인
        int[] nRes; // 센 숫자 칸에 1 입력
        String sTmp; // 양 숫자를 스트링으로
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine().trim());
        for (int test = 0; test < nT; test++) {
            nCount = 0;
            nTmp = 0;
            nRes = new int[10];
            nInput = Integer.parseInt(bf.readLine().trim());
            while (true) {
                nSum = 0;
                nCount++;
                nTmp = nInput * nCount;
                sTmp = Integer.toString(nTmp);

                for (int i = 0; i < sTmp.length(); i++) {
                    nRes[sTmp.charAt(i) - '0'] = 1;
                }

                for (int i = 0; i < 10; i++) {
                    nSum += nRes[i];
                }
                if (nSum == 10) {
                    System.out.println("#" + (test + 1) + " " + nTmp);
                    break;
                }
            }
        }
    }
}
