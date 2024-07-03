import java.io.*;
import java.util.*;

public class SWEA10726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nTestCase;
    static int nLen;
    static long lNum;
    static String sRes;
    static String sBinary;

    static void getBinary() throws IOException {
        StringBuilder sbTmp = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());
        nLen = Integer.parseInt(st.nextToken());
        lNum = Long.parseLong(st.nextToken());

        // String 자료형도 문자열 길이에 제한이 있음 -> 너무 크면 런타임 에러 발생하는 듯
        for (int i = 0; i < nLen; i++) {
            sbTmp.append(lNum % 2);
            lNum /= 2;
        }

        sBinary = sbTmp.toString();
    }

    static void checkOne() {
        sRes = "ON";

        for (int i = 0; i < nLen; i++) {
            if (sBinary.charAt(i) != '1') {
                sRes = "OFF";
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nTestCase; tc++) {
            getBinary();

            checkOne();

            sb.append(String.format("#%d %s\n", tc, sRes));
        }

        System.out.print(sb);
    }
}