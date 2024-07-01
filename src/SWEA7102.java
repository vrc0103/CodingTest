import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA7102 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        int nTestCase;
        int nSet1, nSet2;
        int nMax;
        int[] nCount;

        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            nMax = 0;
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine().trim());
            nSet1 = Integer.parseInt(st.nextToken());
            nSet2 = Integer.parseInt(st.nextToken());
            nCount = new int[nSet1 + nSet2 + 1]; // 인덱스를 N+M으로 계산하기 위해 +1 추가

            for (int i = 1; i <= nSet1; i++) { // 각 숫자가 계산된 횟수 카운트
                for (int j = 1; j <= nSet2; j++) {
                    nCount[i + j]++;
                }
            }

            for (int i = 0; i < nCount.length; i++) { // 가장 많이 등장한 횟수
                nMax = Math.max(nMax, nCount[i]);
            }

            sb.append(String.format("#%d", tc));
            for (int i = 0; i < nCount.length; i++) {
                if (nCount[i] == nMax) {
                    sb.append(String.format(" %d", i));
                }
            }

            System.out.println(sb);
        }
    }
}
