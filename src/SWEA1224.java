/*
 * 연산자는 +, *, 괄호
 * 숫자는 한자릿수만
 * TC의 길이와 식 입력됨
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;

public class SWEA1224 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int GetPriority(char cOperation) {
        int nPriority = 0;

        switch (cOperation) {
            case '+':
                nPriority = 1;
                break;
            case '*':
                nPriority = 2;
                break;
        }

        return nPriority;
    }

    public static int GetRes(int nNum1, int nNum2, char cOpr) {
        int nRes = 0;

        switch (cOpr) {
            case '+':
                nRes = nNum1 + nNum2;
                break;
            case '*':
                nRes = nNum1 * nNum2;
                break;
        }

        return nRes;
    }

    public static void main(String[] args) throws IOException {
        int nTestCase = 10;
        int nLen;
        String sInput;
        Stack<Character> OperationSTK = new Stack<>(); // 연산자
        Stack<Integer> OperandSTK = new Stack<>(); // 숫자
        StringBuilder sPostFix; // 확인용 변환된 후위표기식

        for (int tc = 1; tc <= nTestCase; tc++) {
            nLen = Integer.parseInt(br.readLine().trim());
            sInput = br.readLine().trim();
            sPostFix = new StringBuilder();

            for (int idx = 0; idx < nLen; idx++) { // 후위표기식으로 변환
                if (!Character.isDigit(sInput.charAt(idx))) { // 다음 값이 연산자이면
                    if (OperationSTK.empty()) { // 스택에 첫 입력
                        OperationSTK.push(sInput.charAt(idx));
                    } else if (sInput.charAt(idx) == '(') { // 여는 괄호는 push
                        OperationSTK.push(sInput.charAt(idx));
                    } else if (sInput.charAt(idx) == ')') { // 닫는 괄호는 여는 괄호가 나올때까지 pop
                        while (OperationSTK.peek() != '(') {
                            sPostFix.append(OperationSTK.peek());
                            OperandSTK.push(GetRes(OperandSTK.pop(), OperandSTK.pop(), OperationSTK.pop()));
                            // 숫자 스택에서 두 값을 pop하고 연산자에 맞게 연산하여 새로 push
                        }
                        OperationSTK.pop(); // 저장된 여는 괄호 pop
                    } else { // 스택에 값이 있고, 연산자가 괄호가 아닌 경우 입력할 연산자의 우선순위가 낮거나 같으면 pop
                        while (!OperationSTK.empty()
                                && GetPriority(sInput.charAt(idx)) <= GetPriority(OperationSTK.peek())) {
                            sPostFix.append(OperationSTK.peek());
                            OperandSTK.push(GetRes(OperandSTK.pop(), OperandSTK.pop(), OperationSTK.pop()));
                        }
                        OperationSTK.push(sInput.charAt(idx)); // pop 마치고 새 연산자를 스택에 push
                    }
                } else { // 숫자이면 후위표기식에 추가
                    sPostFix.append(sInput.charAt(idx));
                    OperandSTK.push(sInput.charAt(idx) - '0');
                }
            }
            while (!OperationSTK.empty()) { // 후위표기식 변환 종료시 스택에 남은 모든 연산자 pop
                sPostFix.append(OperationSTK.peek());
                OperandSTK.push(GetRes(OperandSTK.pop(), OperandSTK.pop(), OperationSTK.pop()));
            }

            System.out.println("#" + tc + " " + OperandSTK.pop());
        }
    }
}
