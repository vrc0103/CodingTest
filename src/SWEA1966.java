import java.io.*;
import java.util.*;

public class SWEA1966 {
    public static void main(String[] args) throws IOException {
        int nT, nN;
        int[] nArr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nN = Integer.parseInt(br.readLine().trim());
            nArr = new int[nN];

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < nN; i++) {
                nArr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(nArr);

            System.out.print("#" + test);
            for (int i = 0; i < nN; i++) {
                System.out.print(" " + nArr[i]);
            }
            System.out.println();
        }
    }
}
