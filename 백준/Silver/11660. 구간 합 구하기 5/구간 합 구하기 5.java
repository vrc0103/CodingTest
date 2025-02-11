import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int size, num;
    static int[][] arr;
    static int res;

    public static void main(String[] args) throws IOException {
        getSubSum();

        System.out.print(sb.toString());
    }

    static void getSubSum() throws IOException {
        int x1, y1, x2, y2;

        st = new StringTokenizer(br.readLine().trim());

        // 인덱스 예외를 방지하기 위해 배열 크기를 1 ~ N으로 생성
        size = Integer.parseInt(st.nextToken()) + 1;
        num = Integer.parseInt(st.nextToken());
        arr = new int[size][size];

        // (1, 1)부터의 누적 합 저장
        for (int i = 1; i < size; i++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int j = 1; j < size; j++) {
                // arr[i - 1][j - 1] 까지의 누적 값은 2번 더해지므로 1번 빼줘야 함
                arr[i][j] = arr[i][j - 1] + arr[i - 1][j] - arr[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < num; i++) {
            res = 0;
            st = new StringTokenizer(br.readLine().trim());

            // 1 ~ N으로 생성하였으므로 그냥 씀
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            res = arr[x2][y2] - arr[x1 - 1][y2] - arr[x2][y1 - 1] + arr[x1 - 1][y1 - 1];

            sb.append(res).append("\n");
        }
    }
}
