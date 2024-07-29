/*
 * 문장이 주어지면 각 단어를 역순으로 출력(단어의 순서는 바뀌지 않음)
 * < > 사이에 있는 태그는 바꾸지 않음
 */

import java.io.*;
import java.util.*;

public class BOJ17413 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        getReverse();

        System.out.print(sb);
    }

    static void getReverse() throws IOException {
        int idx;
        int len;
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();

        idx = 0;
        len = str.length();

        while (idx < len) {
            if (str.charAt(idx) == '<') { // 태그 처리
                while (!stack.isEmpty()) { // 이전 단어 먼저 문자열에 저장
                    sb.append(stack.pop());
                }

                while (str.charAt(idx) != '>') { // 태그를 문자열에 저장
                    sb.append(str.charAt(idx++));
                }
                sb.append(str.charAt(idx++));

            } else if (str.charAt(idx) == ' ') { // 띄어쓰기 처리
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(str.charAt(idx++));
            } else { // 일반 단어는 스택에 저장 -> 이후 pop으로 역순 출력
                stack.push(str.charAt(idx++));
            }
        }

        while (!stack.isEmpty()) { // 문장 마지막이 단어인 경우
            sb.append(stack.pop());
        }
    }
}