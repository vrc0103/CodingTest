import java.io.*;
import java.util.*;

public class SWEA1208 {
    public static void main(String[] args) throws IOException {
        int nDump;
        int[] nHeight;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test = 1; test <= 10; test++) {
            nHeight = new int[100];
            nDump = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());

            for (int i = 0; i < 100; i++) { // 높이 저장
                nHeight[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(nHeight); // 1차 정렬
            for (int i = 0; i < nDump; i++) { // 최댓값-1, 최솟값+1
                if (nHeight[99] - nHeight[0] <= 1) { // 평탄화 완료
                    break;
                }
                nHeight[99]--;
                nHeight[0]++;
                Arrays.sort(nHeight);
            }

            System.out.println("#" + test + " " + (nHeight[99] - nHeight[0]));
        }
    }
}
