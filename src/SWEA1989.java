/*
 * 단어를 입력받아 회문(대칭)이면 1, 아니면 0 출력
 */

import java.io.*;

public class SWEA1989 {
    public static int wordCheck(String sWord) {
        int nLen = sWord.length();
        int nCheck = 0;
        char[] cWord = new char[nLen];

        for (int idx = 0; idx < nLen; idx++) { // String을 char 배열로 변환
            cWord[idx] = sWord.charAt(idx);
        }
        for (int idx = 0; idx < nLen / 2; idx++) { // 양 끝에서부터 동일한 글자인지 비교
            if (cWord[idx] == cWord[nLen - 1 - idx]) {
                nCheck = 1;
            } else {
                nCheck = 0;
                break;
            }
        }

        return nCheck;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        String sWord;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine().trim());
        for (int test = 0; test < nT; test++) {
            sWord = bf.readLine().trim();

            System.out.print("#" + (test + 1) + " ");
            if (wordCheck(sWord) == 1) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
        }
    }
}
