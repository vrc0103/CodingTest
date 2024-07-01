import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

/*
 * 첫 줄에 TC
 * 연산 갯수
 * 연산1 : 1 x -> x 삽입
 * 연산2 : root 반환
 */

public class SWEA2930 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int nTestCase;
    public static int nInstLen;
    public static ArrayList<Integer> myHeap;

    public static void insertHeap(int nInput) {
        int nNodeNum;
        int nParent, nChild;

        myHeap.add(nInput);
        nNodeNum = myHeap.size() - 1;

        while (nNodeNum > 0) { // Max Heap 정렬
            nParent = myHeap.get(nNodeNum / 2);
            nChild = myHeap.get(nNodeNum);
            if (nChild > nParent) {
                myHeap.set(nNodeNum / 2, nChild);
                myHeap.set(nNodeNum, nParent);
                nNodeNum /= 2;
            } else {
                break;
            }
        }
    }

    public static int deleteHeap() {
        int nRes;
        int nTmp;
        int nNode;
        int nPrnt, nLC, nRC;

        if (myHeap.size() == 1) { // 힙이 빈 경우(0은 남아있으므로 size = 1)
            return -1;
        }

        if (myHeap.size() == 2) { // 힙에 값이 하나만 있는 경우
            return myHeap.remove(1);
        }

        else {
            nNode = 1;
            nRes = myHeap.get(1);
            nTmp = myHeap.remove(myHeap.size() - 1);
            myHeap.set(1, nTmp);

            while (2 * nNode <= myHeap.size() - 1) { // 자식 노드가 하나라도 있는 경우에만 반복

                if (2 * nNode + 1 <= myHeap.size() - 1) { // 양쪽 자식노드 존재
                    nPrnt = myHeap.get(nNode);
                    nLC = myHeap.get(2 * nNode);
                    nRC = myHeap.get(2 * nNode + 1);

                    if (Math.max(nPrnt, Math.max(nLC, nRC)) == nLC) { // 왼쪽 자식 노드의 값이 더 큰 경우
                        myHeap.set(nNode, nLC);
                        myHeap.set(2 * nNode, nPrnt);
                        nNode *= 2;
                    } else if (Math.max(nPrnt, Math.max(nLC, nRC)) == nRC) { // 오른쪽 자식 노드의 값이 더 큰 경우
                        myHeap.set(nNode, nRC);
                        myHeap.set(2 * nNode + 1, nPrnt);
                        nNode = 2 * nNode + 1;
                    } else { // 부모 노드(현재)가 가장 큰 경우
                        break;
                    }
                } else if (2 * nNode <= myHeap.size() - 1) { // 왼쪽 자식노드만 존재
                    nPrnt = myHeap.get(nNode);
                    nLC = myHeap.get(2 * nNode);

                    if (Math.max(nPrnt, nLC) == nLC) { // 왼쪽 자식 노드의 값이 더 큰 경우
                        myHeap.set(nNode, nLC);
                        myHeap.set(2 * nNode, nPrnt);
                        nNode *= 2;
                    } else {
                        break;
                    }
                }
            }
        }

        return nRes;
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nTestCase; tc++) {
            myHeap = new ArrayList<>();
            myHeap.add(Integer.MAX_VALUE); // 0번 인덱스 처리

            nInstLen = Integer.parseInt(br.readLine().trim());

            sb.append(String.format("#%d", tc));

            for (int idx = 0; idx < nInstLen; idx++) {
                st = new StringTokenizer(br.readLine().trim());
                if (Integer.parseInt(st.nextToken()) == 1) { // Insert
                    insertHeap(Integer.parseInt(st.nextToken()));
                } else { // Delete
                    sb.append(String.format(" %d", deleteHeap()));
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
