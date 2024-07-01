import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA1232 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int nTestCase = 10;
    public static int nTreeSize;
    public static int nNodeNum, nLC, nRC;
    public static double dRes;
    public static String sInput;
    public static String[] sOperator = { "+", "-", "*", "/" };
    public static MyTree[] myTree;

    public static class MyTree {
        int nNode;
        int nLeftChild;
        int nRightChild;
        String sTxt;

        MyTree(int nNode, String sTxt, int nLeftChild, int nRightChild) {
            this.nNode = nNode;
            this.sTxt = sTxt;
            this.nLeftChild = nLeftChild;
            this.nRightChild = nRightChild;
        }
    }

    public static boolean isOperator(String sTmp) { // 연산자인지 확인
        boolean bJudg = false;

        for (String Operator : sOperator) {
            if (sTmp.equals(Operator)) {
                bJudg = true;
                break;
            }
        }

        return bJudg;
    }

    public static double getRes(int nNode) { // 실수 사칙 연산
        double dNum1, dNum2;

        if (isOperator(myTree[myTree[nNode].nLeftChild].sTxt)) { // 왼쪽 자식 노드가 연산자
            dNum1 = getRes(myTree[nNode].nLeftChild);
        } else { // 왼쪽 자식 노드가 숫자
            dNum1 = Double.parseDouble(myTree[myTree[nNode].nLeftChild].sTxt);
        }

        if (isOperator(myTree[myTree[nNode].nRightChild].sTxt)) { // 오른쪽 자식 노드가 연산자
            dNum2 = getRes(myTree[nNode].nRightChild);
        } else { // 오른쪽 자식 노드가 숫자
            dNum2 = Double.parseDouble(myTree[myTree[nNode].nRightChild].sTxt);
        }

        switch (myTree[nNode].sTxt) {
            case "+":
                dRes = dNum1 + dNum2;
                break;
            case "-":
                dRes = dNum1 - dNum2;
                break;
            case "*":
                dRes = dNum1 * dNum2;
                break;
            case "/":
                dRes = dNum1 / dNum2;
                break;
        }

        return dRes;
    }

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= nTestCase; tc++) {
            dRes = 0;
            sb = new StringBuilder();

            nTreeSize = Integer.parseInt(br.readLine().trim());
            myTree = new MyTree[nTreeSize + 1]; // 1부터 시작 -> +1필요

            for (int idx = 0; idx < nTreeSize; idx++) { // 트리를 배열로 구현
                st = new StringTokenizer(br.readLine().trim());

                nNodeNum = Integer.parseInt(st.nextToken());
                sInput = st.nextToken();
                if (st.hasMoreTokens()) {
                    nLC = Integer.parseInt(st.nextToken());
                    nRC = Integer.parseInt(st.nextToken());
                } else {
                    nLC = nRC = 0;
                }

                myTree[nNodeNum] = new MyTree(nNodeNum, sInput, nLC, nRC);
            }

            sb.append(String.format("#%d %d", tc, (int) getRes(1)));
            System.out.println(sb);
        }
    }
}
