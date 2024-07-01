/*
 * 첫 줄에 테스트케이스
 * 정점 갯수 V, 간선갯수E, 공통 조상을 찾을 두 노드의  번호
 * 연결될 노드가 부모 - 자식 순서로 입력됨
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class SWEA1248 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int nTestCase;
    public static int nNodeCount;
    public static int nParent, nChild;
    public static int nTarget1, nTarget2;
    public static int nSubSize;
    public static ArrayList<Integer> nAnce;
    public static MyTree[] myTree;

    public static class MyTree {
        int nParent;
        ArrayList<Integer> nChildArr;

        MyTree(int nParent) {
            this.nParent = nParent;
            nChildArr = new ArrayList<>();
        }

        public void setChild(int nChildNode) { // 자식 노드의 정보
            nChildArr.add(nChildNode);
        }
    }

    public static void getSubSize(int nNodeNum) {
        nSubSize++;

        while (!myTree[nNodeNum].nChildArr.isEmpty()) { // 자식 노드가 있으면 해당 노드로 재귀 호출 -> DFS 방식
            getSubSize(myTree[nNodeNum].nChildArr.remove(0));
        }
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nTestCase; tc++) {
            nSubSize = 0;
            sb = new StringBuilder();
            nAnce = new ArrayList<>();

            st = new StringTokenizer(br.readLine().trim());
            nNodeCount = Integer.parseInt(st.nextToken());
            st.nextToken(); // 간선 수 무의미함
            nTarget1 = Integer.parseInt(st.nextToken());
            nTarget2 = Integer.parseInt(st.nextToken());

            myTree = new MyTree[nNodeCount + 1];
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < nNodeCount - 1; idx++) { // 자식노드(인덱스)의 값에 부모노드 저장, 2번 노드부터 입력하므로 반복 1회 축소
                nParent = Integer.parseInt(st.nextToken());
                nChild = Integer.parseInt(st.nextToken());

                if (myTree[nParent] == null) { // 부모 노드 생성
                    myTree[nParent] = new MyTree(0); // 부모 노드의 parent는 0으로 임시
                }
                myTree[nParent].setChild(nChild); // 자식 노드의 번호를 부모 노드에 저장

                if (myTree[nChild] == null) { // 자식 노드 생성
                    myTree[nChild] = new MyTree(nParent);
                } else { // 이미 있는 경우
                    myTree[nChild].nParent = nParent;
                }
            }

            while (nTarget1 != 0) { // 타겟1의 모든 조상 노드를 배열에 저장
                nAnce.add(nTarget1);
                nTarget1 = myTree[nTarget1].nParent;
            }

            while (true) { // 타겟2의 조상 노드와 비교, 공통 조상을 찾으면 탈출
                if (nAnce.contains(nTarget2)) {
                    break;
                }
                nTarget2 = myTree[nTarget2].nParent;
            }
            getSubSize(nTarget2);
            sb.append(String.format("#%d %d %d", tc, nTarget2, nSubSize));
            System.out.println(sb);
        }
    }
}
