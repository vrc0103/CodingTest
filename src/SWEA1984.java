import java.io.*;
import java.util.*;

public class SWEA1984 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nSum, nRes;
        int[] nArr = new int[10];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nSum = 0;
            st = new StringTokenizer(br.readLine().trim());

            for (int i = 0; i < 10; i++) {
                nArr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(nArr);
            for (int i = 1; i < 9; i++) {
                nSum += nArr[i];
            }

            nRes = nSum / 8;
            if (nSum % 8 >= 4) { // 소숫점 첫째자리 반올림
                nRes++;
            }

            System.out.println("#" + test + " " + nRes);
        }
    }
}
