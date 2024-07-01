import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * 붙어있는 ()는 레이저의 위치
 * 레이저를 끼고있는 (  )는 쇠파이프
 * 괄호의 배치가 주어졌을 때 잘린 쇠파이프의 갯수
 * 
 * 여는 괄호가 파이프의 시작 -> 여는 괄호의 갯수가 주어진 파이프의 갯수 (레이저는 빼야함)
 * 닫는 괄호가 파이프의 끝 -> 괄호(파이프) 내의 레이저 갯수 +1이 잘린 후의 파이프 갯수
 */

public class SWEA5432 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int nTestCase;
        int nResPipe; // 잘린 후의 파이프 갯수
        int nCountPipe; // 괄호 열리고 닫힌 갯수 파악
        String sPipe; // 입력받은 파이프-레이저 문장

        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            nResPipe = 0;
            nCountPipe = 0;
            sPipe = br.readLine().trim();

            for (int idx = 0; idx < sPipe.length(); idx++) {

                if (idx != sPipe.length() - 1 && sPipe.charAt(idx) == '(') {
                    if (sPipe.charAt(idx + 1) != ')') {
                        // 괄호가 열리고 바로 안닫히면 파이프
                        nResPipe++;
                        nCountPipe++;
                    } else {
                        // 바로 닫히면 레이저 -> 파이프가 잘렸으므로 카운트된 파이프의 갯수만큼 전체 파이프 증가
                        nResPipe += nCountPipe;
                    }
                }
                if (idx > 0 && sPipe.charAt(idx) == ')' && sPipe.charAt(idx - 1) == ')') {
                    // 닫는 괄호인데 이전 괄호도 닫는 괄호이면 파이프 끝 -> 카운트할 파이프의 갯수 감소
                    nCountPipe--;
                }
            }
            System.out.println("#" + tc + " " + nResPipe);
        }
    }
}
