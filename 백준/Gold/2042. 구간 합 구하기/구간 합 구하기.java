import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int size, numUpdate, numSum;
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

        arr = new long[size + 1];
        for (int i = 1; i <= size; i++) {
            arr[i] = Long.parseLong(br.readLine().trim());
        }

        // 초기 세그먼트 트리 구현
        tree = new long[4 * size]; // 정확하진 않지만 4 * size로 해도 충분함

        init(1, 1, size);

        // 명령어에 따른 계산, 갱신
        for (int i = 0; i < numSum + numUpdate; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int cmd = Integer.parseInt(st.nextToken());

            // update
            if (cmd == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long newVal = Long.parseLong(st.nextToken());

                update(1, 1, size, idx, newVal);
            }
            // sum
            else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                sb.append(sum(1, 1, size, left, right)).append("\n");
            }
        }
    }

    static void init(int now, int start, int end) {
        // 리프 노드이면 입력받은 값 저장
        if (start == end) {
            tree[now] = arr[start];
        }
        // 중간 노드이면 재귀 수행 후 누적값 저장
        else {
            int mid = (start + end) / 2;
            init(now * 2, start, mid);
            init(now * 2 + 1, mid + 1, end);

            tree[now] = tree[now * 2] + tree[now * 2 + 1];
        }
    }

    static void update(int now, int start, int end, int idx, long newVal) {
        // 범위를 벗어난 경우 무시
        if (idx < start || end < idx) {
            return;
        }

        // build() 메서드와 유사
        if (start == end) {
            tree[now] = newVal;
        } else {
            int mid = (start + end) / 2;
            update(now * 2, start, mid, idx, newVal);
            update(now * 2 + 1, mid + 1, end, idx, newVal);

            tree[now] = tree[now * 2] + tree[now * 2 + 1];
        }
    }

    static long sum(int now, int start, int end, int left, int right) {
        // 아예 겹치지 않는 경우
        if (right < start || end < left) {
            return 0;
        }

        // 완전히 포함되는 경우
        if (left <= start && end <= right) {
            return tree[now];
        }

        // 일부만 포함되는 경우 좌우 자식 노드의 결과를 합산
        int mid = (start + end) / 2;
        long lsum = sum(now * 2, start, mid, left, right);
        long rsum = sum(now * 2 + 1, mid + 1, end, left, right);

        return lsum + rsum;
    }
}
