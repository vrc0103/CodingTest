import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SWEA1218 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int nTestCase = 10;
        int[] nCount; // () {} [] <> 카운트
        int nLen;
        int nJudg;
        String sParam = "(){}[]<>";
        String sInput;

        for (int tc = 1; tc <= nTestCase; tc++) {
            nJudg = 0;
            nCount = new int[8];
            nLen = Integer.parseInt(br.readLine().trim());
            sInput = br.readLine().trim();

            if (nLen % 2 == 0) { // 일단 입력 갯수가 짝수여야 짝이 맞을 가능성 있음
                for (int idx = 0; idx < nLen; idx++) {
                    nCount[sParam.indexOf(sInput.charAt(idx))]++; // sParam에서 동일한 괄호를 찾아 nCount의 값 증가
                }
                for (int idx = 0; idx < 4; idx++) {
                    if (nCount[2 * idx] != nCount[2 * idx + 1]) {
                        break;
                    }
                    if (idx == 3) { // break되지 않고 끝까지 돌면 각 괄호의 열리고 닫힌 갯수 맞음
                        nJudg = 1;
                    }
                }
            }

            System.out.println("#" + tc + " " + nJudg);
        }
    }
}
