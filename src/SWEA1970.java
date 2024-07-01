import java.io.*;

public class SWEA1970 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nInput;
        int[] nMnyArr = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            System.out.println("#" + test);
            nInput = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < nMnyArr.length; i++) {
                System.out.print(nInput / nMnyArr[i] + " ");
                nInput %= nMnyArr[i];
            }
            System.out.println();
        }
    }
}
