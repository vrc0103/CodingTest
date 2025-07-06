import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int size, numUpdate, numSum;
    static int treeHeight;
    static long[] arr, tree;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        // 입력값 저장
        st = new StringTokenizer(br.readLine().trim());

        size = Integer.parseInt(st.nextToken());
        numUpdate = Integer.parseInt(st.nextToken());
        numSum = Integer.parseInt(st.nextToken());

        arr = new long[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Long.parseLong(br.readLine().trim());
        }

        // 초기 세그먼트 트리 구현
        init();

        // 명령어에 따른 계산, 갱신
        for (int i = 0; i < numSum + numUpdate; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int cmd = Integer.parseInt(st.nextToken());

            // update
            if (cmd == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long newVal = Long.parseLong(st.nextToken());

                update(idx, newVal);
            }
            // sum
            else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                sb.append(sum(left, right)).append("\n");
            }
        }
    }

    static void init() {
        treeHeight = (int) Math.ceil(Math.log(size) / Math.log(2));
        tree = new long[1 << (treeHeight + 1)];

        int leafStart = 1 << treeHeight;

        // 리프 노드
        for (int i = 0; i < size; i++) {
            tree[leafStart + i] = arr[i];
        }

        // 중간 노드
        for (int i = leafStart - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static void update(int idx, long newVal) {
        // idx번에 해당하는 리프 노드 갱신
        int now = (1 << treeHeight) + idx - 1;
        tree[now] = newVal;

        // 누적값 갱신
        while (now > 1) {
            now /= 2;
            tree[now] = tree[now * 2] + tree[now * 2 + 1];
        }
    }

    static long sum(int left, int right) {
        // 일반 배열의 인덱스를 리프 노드의 인덱스로 변경
        int now = (1 << treeHeight);
        int l = now + left - 1;
        int r = now + right - 1;
        long res = 0;

        while (l <= r) {
            // 시작점이 오른쪽 자식 노드 -> 부모로 이동하기 전에 해당 노드만 합산
            if (l % 2 == 1) {
                res += tree[l++];
            }
            // 끝점이 왼쪽 자식 노드 -> 부모로 이동하기 전에 해당 노드만 합산
            if (r % 2 == 0) {
                res += tree[r--];
            }

            // 나머지는 양쪽 자식 모두 계산에 포함되므로 부모 노드로 이동
            l /= 2;
            r /= 2;
        }

        return res;
    }
}
