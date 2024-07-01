import java.io.*;
import java.util.*;

public class SWEA1206 {
    public static int nN;
    public static int[] nHeightComp = new int[4];
    public static int nRes;
    public static int[] nHeight;
    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int test = 1; test <= 10; test++) {
            nRes = 0;
            sb = new StringBuilder();
            nN = Integer.parseInt(br.readLine().trim());
            nHeight = new int[nN];
            st = new StringTokenizer(br.readLine().trim());

            for (int i = 0; i < nN; i++) { // 각 건물별 높이 저장
                nHeight[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 2; i < nN - 2; i++) {
                nHeightComp[0] = nHeight[i] - nHeight[i - 1];
                nHeightComp[1] = nHeight[i] - nHeight[i - 2];
                nHeightComp[2] = nHeight[i] - nHeight[i + 1];
                nHeightComp[3] = nHeight[i] - nHeight[i + 2];

                if (nHeightComp[0] > 0 && nHeightComp[1] > 0 && nHeightComp[2] > 0 && nHeightComp[3] > 0) { // 조망권 확보
                    Arrays.sort(nHeightComp);
                    nRes += nHeightComp[0];
                }
            }

            sb.append("#").append(test).append(" ").append(nRes);
            System.out.println(sb);
        }
    }
}
