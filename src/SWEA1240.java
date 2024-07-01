import java.io.*;
import java.util.*;

/*
 * 암호는 8개의 숫자, 각 숫자는 7bit로 구성
 * (홀수자리 합 * 3) + (짝수자리 합) = 10의 배수이면 올바른 암호
 */

public class SWEA1240 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nTestCase;
    static int nRow, nCol;
    static int nDec;
    static int[] nCount;
    static int[] nEnc;

    static int getNum() throws IOException {
        int nNum = 0;
        String sTmp;
        StringBuilder sbTmp = new StringBuilder();

        for (int i = 1; i < 4; i++) { // 1, 0, 1 비율을 문자열로 변환
            sbTmp.append(nCount[i]);
        }
        sTmp = sbTmp.toString();

        switch (sTmp) {
            case "211":
                nNum = 0;
                break;
            case "221":
                nNum = 1;
                break;
            case "122":
                nNum = 2;
                break;
            case "411":
                nNum = 3;
                break;
            case "132":
                nNum = 4;
                break;
            case "231":
                nNum = 5;
                break;
            case "114":
                nNum = 6;
                break;
            case "312":
                nNum = 7;
                break;
            case "213":
                nNum = 8;
                break;
            case "112":
                nNum = 9;
                break;
        }

        return nNum;
    }

    static void getEnc() throws IOException {
        int idx = 0;
        int nNum = 0;
        String sInput;

        nCount = new int[4];
        nEnc = new int[8];

        st = new StringTokenizer(br.readLine().trim());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());

        for (int row = 0; row < nRow; row++) { // 암호문 숫자 8자리를 모두 찾으면 종료
            sInput = br.readLine().trim();

            for (int col = 1; col < nCol; col++) {
                if (nNum == 8) { // 암호문 숫자 모두 찾은 경우 반복문 수행 X
                    break;
                }
                if ((sInput.charAt(col - 1) - '0') + (sInput.charAt(col) - '0') == 1) {
                    idx++; // 0 -> 1 또는 1 -> 0이 되면 배열의 다음 인덱스로 변경
                }

                if (idx == 4) { // 숫자 한자리 확인 완료
                    nEnc[nNum++] = getNum();

                    idx = 0;
                    nCount = new int[4]; // 비율 배열 초기화
                }

                // nCount 배열의 1, 2, 3번 인덱스가 각각 1, 0, 1의 비율을 나타냄
                // 0번 인덱스는 처음 시작점을 잡기 어렵기도 하고, 1 2 3번 인덱스만으로 구분이 가능하므로 취급X
                nCount[idx]++;
            }
        }
    }

    static void judgeEnc() {
        int nJudge;

        nJudge = 0;
        nDec = 0;

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) { // 홀수 자리는 3배
                nJudge += nEnc[i] * 3;
                nDec += nEnc[i];
            } else { // 짝수 자리는 그냥
                nJudge += nEnc[i];
                nDec += nEnc[i];
            }
        }

        if (nJudge % 10 != 0) { // 올바르지 않은 암호문
            nDec = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nTestCase; tc++) {
            getEnc();

            judgeEnc();

            sb.append(String.format("#%d %d\n", tc, nDec));
        }

        System.out.print(sb);
    }
}
