import java.io.*;
import java.util.*;

public class SWEA1238 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase = 10;
    static int len;
    static int start;
    static int res;
    static ArrayList<Integer>[] contact;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= testCase; tc++) {
            getContact();

            getFinal();

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb.toString());
    }

    static void getContact() throws IOException {
        int from, to;

        contact = new ArrayList[101];

        st = new StringTokenizer(br.readLine());

        len = Integer.parseInt(st.nextToken()) / 2;
        start = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < len; i++) {
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            if (contact[from] == null) {
                contact[from] = new ArrayList<Integer>();
            }

            contact[from].add(to);
        }
    }

    static void getFinal() {
        int now;
        int count;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<>();

        selected = new boolean[101];
        selected[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            // 이번 사이클에 연락할 인원 수
            count = queue.size();

            // 다음 사이클에 연락할 사람이 있으면 연락 정보 초기화
            arr.clear();

            for (int i = 0; i < count; i++) {
                now = queue.poll();
                arr.add(now);

                // 연락처 X
                if (contact[now] == null) {
                    continue;
                }

                for (int next : contact[now]) {
                    if (!selected[next]) {
                        selected[next] = true;
                        queue.add(next);
                    }
                }
            }
        }

        // 마지막으로 연락 받는 사람 중 가장 큰 숫자
        Collections.sort(arr);
        res = arr.get(arr.size() - 1);
    }
}
