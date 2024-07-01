import java.io.*;
import java.util.*;

public class SWEA1946 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nN;
        char cC;
        int nK;
        int nIdx;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nIdx = 0;
            nN = Integer.parseInt(br.readLine().trim());
            System.out.println("#" + test);
            for (int i = 0; i < nN; i++) {
                st = new StringTokenizer(br.readLine().trim());
                cC = st.nextToken().charAt(0);
                nK = Integer.parseInt(st.nextToken());

                for (int decode = 0; decode < nK; decode++) {
                    System.out.print(cC);
                    nIdx++;
                    if (nIdx == 10) { // 10글자 모두 채우면 개행
                        nIdx = 0;
                        System.out.println();
                    }
                }
            }
            System.out.println();
        }
    }
}
