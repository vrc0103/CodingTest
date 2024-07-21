/*
 * 0 a b : a가 포함된 부분집합과 b가 포함된 부분집합을 합침
 * 1 a b : a와 b가 같은 부분집합에 속해있는지 확인
 */

import java.io.*;
import java.util.*;

public class BJ1717 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int maxNum;
    static int numOfOper;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        checkSubset();

        System.out.print(sb);
    }

    static void checkSubset() throws IOException {
        int oper, a, b;

        st = new StringTokenizer(br.readLine().trim());
        maxNum = Integer.parseInt(st.nextToken());
        numOfOper = Integer.parseInt(st.nextToken());

        parent = new int[maxNum + 1];
        for (int i = 0; i < maxNum + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < numOfOper; i++) {
            st = new StringTokenizer(br.readLine().trim());
            oper = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // subset 배열 전체 탐색 : 시간 초과
            // 2차원 ArrayList : 메모리 초과
            // Union - Find 알고리즘 https://brenden.tistory.com/33
            if (oper == 0) { // 부분집합 결합
                if (a == b) { // a, b가 동일하면 다음 명령어 수행
                    continue;
                }

                union(a, b);
            } else { // 부분집합 확인
                if (getParent(a) == getParent(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
    }

    static void union(int a, int b) {
        int aParent = getParent(a);
        int bParent = getParent(b);

        if (aParent < bParent) { // 두 수의 조상을 비교하여 큰 숫자의 조상에 작은 숫자 저장
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

    static int getParent(int a) { // 부분집합 내의 가장 작은 숫자(조상) 반환
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = getParent(parent[a]); // parent 배열을 갱신하고 반환해야 시간이 절약됨
    }
}
