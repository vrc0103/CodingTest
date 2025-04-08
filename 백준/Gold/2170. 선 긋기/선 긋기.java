import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int cnt;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        ArrayList<int[]> list = new ArrayList<>();
        cnt = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new int[] {start, end});
        }

        // 시작점 오름차순, 끝점 오름차순
        Collections.sort(list, (o1, o2) -> {
            if(o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        // 정렬된 리스트를 탐색하며 연결된 선 파악
        for(int i = 0; i < cnt; i++) {
            int start = list.get(i)[0];
            int end = list.get(i)[1];

            while(++i < cnt && end >= list.get(i)[0]) {
                if(end < list.get(i)[1]) {
                    end = list.get(i)[1];
                }
            }

            // 연결이 끊긴 선은 다시 탐색 가능하도록 인덱스 1 감소
            i--;

            res += end - start;
        }
    }
}
