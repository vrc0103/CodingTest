import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int numPerArea;
    static int row, col;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        setInfo();

        getMelon();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        int dir, len;

        numPerArea = Integer.parseInt(br.readLine().trim());
        row = col = 0;

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine().trim());

            dir = Integer.parseInt(st.nextToken());
            len = Integer.parseInt(st.nextToken());

            queue.add(new int[] { dir, len });

            // 가로 방향 길이
            if (dir <= 2) {
                col = Math.max(col, len);
            }
            // 세로 방향 길이
            else {
                row = Math.max(row, len);
            }
        }
    }

    static void getMelon() {
        int area;
        int[] now, prev1, prev2, prev3;

        area = row * col;

        prev3 = queue.remove();
        prev2 = queue.remove();
        prev1 = queue.remove();

        while (true) {
            now = queue.remove();

            // 밭에서 제외되는 부분 탐색 : 가로, 세로 방향이 2번 앞의 방향과 각각 동일한 경우
            if (now[0] == prev2[0] && prev1[0] == prev3[0]) {
                break;
            }

            queue.add(prev3);
            prev3 = prev2;
            prev2 = prev1;
            prev1 = now;
        }

        area -= prev1[1] * prev2[1];

        res = numPerArea * area;
    }
}
