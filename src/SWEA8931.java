import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class SWEA8931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int nTestCase;
        int nRes;
        int nCount;
        int nTmp;
        Stack<Integer> stk = new Stack<>();

        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            nRes = 0;
            sb = new StringBuilder();

            nCount = Integer.parseInt(br.readLine().trim());
            for (int idx = 0; idx < nCount; idx++) { // 입력받은 값들 스택에 저장
                nTmp = Integer.parseInt(br.readLine().trim());
                if (nTmp != 0) {
                    stk.push(nTmp);
                } else {
                    stk.pop();
                }
            }
            while (!stk.isEmpty()) {
                nRes += stk.pop();
            }

            sb.append("#").append(tc).append(" ").append(nRes);
            System.out.println(sb);
        }
    }
}
