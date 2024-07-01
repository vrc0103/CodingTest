/*
 * 
 */

import java.io.*;

public class SWEA2007 {
    public static int patCheck(String sInput) {
        int patLen = 0;
        char cWordFirst;
        char[] cWord = new char[sInput.length()];

        for (int idx = 0; idx < sInput.length(); idx++) {
            cWord[idx] = sInput.charAt(idx);
        }

        cWordFirst = cWord[0]; // 패턴 시작 글자
        for (patLen = 1; patLen < sInput.length(); patLen++) {
            if (cWord[patLen] == cWordFirst) { // n번째 글자가 첫 글자와 동일
                if (sInput.substring(0, patLen).equals(sInput.substring(patLen, 2 * patLen))) { // 두 단어 배열이 동일한지 확인
                    break;
                }
            }
        }
        return patLen;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        String sInput;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine().trim());
        for (int test = 0; test < nT; test++) {
            sInput = bf.readLine().trim();
            System.out.println("#" + (test + 1) + " " + patCheck(sInput));
        }
    }
}
