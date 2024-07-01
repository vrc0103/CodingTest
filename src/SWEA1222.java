/*
 * 연산자는 덧셈만
 * 한자릿수 숫자만 존재
 */

import java.io.*;
import java.util.*;

public class SWEA1222 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sPostOrd; // 후위표기식

    public static void main(String[] args) throws IOException {
        int nTestCase = 10;
        int nLen;
        int nRes;
        String sInput;
        Stack<Character> cStk = new Stack<>();

        for (int tc = 1; tc <= nTestCase; tc++) {
            nRes = 0;
            sPostOrd = new StringBuilder();

            nLen = Integer.parseInt(br.readLine().trim());
            sInput = br.readLine().trim();
            for (int i = 0; i < nLen; i++) {
                if (sInput.charAt(i) == '+') { // 연산자인 경우 - 이번 문제는 덧셈만 입력됨
                    if (cStk.empty()) { // 연산자 스택 빈칸이면 바로 push
                        cStk.push(sInput.charAt(i));
                    } else { // 이후 새 연산자 입력
                        sPostOrd.append(cStk.pop()); // 덧셈만 들어옴 -> 동일 우선순위이므로 그냥 pop
                        cStk.push(sInput.charAt(i));
                    }
                } else {
                    sPostOrd.append(sInput.charAt(i));
                    nRes += sInput.charAt(i) - '0';
                }
            }
            while (!cStk.empty()) {
                sPostOrd.append(cStk.pop()); // 식이 종료되면 연산자 스택에 남아있던 연산자 모두 pop
            }

            System.out.println("#" + tc + " " + nRes); // + " / " + sPostOrd);
        }
    }
}
