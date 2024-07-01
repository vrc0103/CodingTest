/*
 * 첫 줄에 케이스 갯수 T
 * 글자를 6비트로 대응시켜 24비트씩 묶고, 8비트 3글자로 치환
 */

import java.io.*;

public class SWEA1928 {
    public static String Decode64(String sEncoded) {
        int sLen = sEncoded.length();
        int nConv = 0;
        char cTmp;
        String sBinary = "";
        String sTmp;
        String sDecoded = "";

        for (int i = 0; i < sLen; i++) {
            cTmp = sEncoded.charAt(i); //// String 입력을 char로 쪼개서 표1의 인코딩 숫자로 저장

            if (cTmp >= 'A' && cTmp <= 'Z') // A ~ Z
                nConv = cTmp - 'A'; // A=0 ~ Z=25
            else if (cTmp >= 'a' && cTmp <= 'z') // a ~ z
                nConv = cTmp - 'a' + 26; // a=26 ~ z=51
            else if (cTmp >= '0' && cTmp <= '9') // 0 ~ 9
                nConv = cTmp - '0' + 52; // 0=52 ~ 9=61
            else if (cTmp == '+')
                nConv = 62;
            else if (cTmp == '/')
                nConv = 63;

            // 6자리로 맞춰서 문자열에 추가
            sBinary += String.format("%6s", Integer.toBinaryString(nConv)).replace(" ", "0");
        }
        while (!sBinary.isEmpty()) {
            sTmp = sBinary.substring(0, 8);
            sBinary = sBinary.substring(8);
            cTmp = (char) Integer.parseInt(sTmp, 2); // parseInt로 2진수 배열을 10진수로 변환 후 char형으로 변환

            sDecoded += cTmp;
        }

        return sDecoded;
    }

    public static void main(String[] args) throws IOException {
        int nT;
        String sEncoded, sDecoded;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine().trim());

        for (int i = 0; i < nT; i++) {
            sEncoded = bf.readLine().trim(); // 인코딩된 문장
            sDecoded = Decode64(sEncoded);

            System.out.println("#" + (i + 1) + " " + sDecoded);
        }
    }
}
