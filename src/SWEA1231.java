import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA1231 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int nTestCase = 10;
    public static int nTreeSize;
    public static int nNode;
    public static int nLChild, nRChild;
    public static char cWord;

    public static MyTree[] myTree;

    public static class MyTree {
        char cWord;
        int nLChild, nRChild;

        MyTree(char cWord, int nLChild, int nRChild) {
            this.cWord = cWord;
            this.nLChild = nLChild;
            this.nRChild = nRChild;
        }
    }

    public static void getInorder(int nNodeNum) {
        // 왼쪽 자식 노드가 있으면 해당 노드로 이동
        if (myTree[nNodeNum].nLChild != 0) {
            getInorder(myTree[nNodeNum].nLChild);
        }

        // 현재 노드의 값 추가
        sb.append(myTree[nNodeNum].cWord);

        // 오른쪽 자식 노드가 있으면 해당 노드로 이동
        if (myTree[nNodeNum].nRChild != 0) {
            getInorder(myTree[nNodeNum].nRChild);
        }
    }

    public static void main(String[] args) throws IOException {

        for (int tc = 1; tc <= nTestCase; tc++) {
            sb.setLength(0); // sb 초기화

            nTreeSize = Integer.parseInt(br.readLine().trim());
            myTree = new MyTree[nTreeSize + 1]; // 클래스 배열

            for (int idx = 0; idx < nTreeSize; idx++) { // 클래스 배열 초기화
                st = new StringTokenizer(br.readLine());
                nNode = Integer.parseInt(st.nextToken());
                cWord = st.nextToken().charAt(0);
                if (st.hasMoreTokens()) { // 왼쪽 자식노드 존재
                    nLChild = Integer.parseInt(st.nextToken());
                    if (st.hasMoreTokens()) { // 오른쪽 자식노드 존재
                        nRChild = Integer.parseInt(st.nextToken());
                    } else {
                        nRChild = 0;
                    }
                } else {
                    nLChild = nRChild = 0;
                }
                myTree[nNode] = new MyTree(cWord, nLChild, nRChild); // 각 클래스 초기화
            }

            sb.append(String.format("#%d ", tc));
            getInorder(1);
            System.out.println(sb);
        }
    }
}
