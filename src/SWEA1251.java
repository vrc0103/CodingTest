import java.io.*;
import java.util.*;

public class SWEA1251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int num;
    static int[] islandR;
    static int[] islandC;
    static long res;
    static double tax;
    static boolean[] nodes;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            getIslands();

            getMST();

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb.toString());
    }

    static void getIslands() throws IOException {
        num = Integer.parseInt(br.readLine());
        islandR = new int[num];
        islandC = new int[num];
        nodes = new boolean[num];

        st = new StringTokenizer(br.readLine());
        for (int r = 0; r < num; r++) {
            islandR[r] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < num; c++) {
            islandC[c] = Integer.parseInt(st.nextToken());
        }

        tax = Double.parseDouble(br.readLine());
    }

    // 프림 알고리즘 개념만 알아서 구현해봤는데 시간이 오래 걸림(통과는 함)
    static void getMST() {
        int isle = 0;
        int count = 1;
        long min, tmp;

        nodes[isle] = true;
        res = 0;

        while (count < num) {
            min = Long.MAX_VALUE;

            // 방문한 노드 기준으로
            for (int i = 0; i < num; i++) {
                if (nodes[i]) {
                    // 방문하지 않은 노드까지의 거리 중 최소 거리와 해당 노드 탐색
                    for (int j = 0; j < num; j++) {
                        if (!nodes[j]) {
                            tmp = (long) (Math.pow(islandR[j] - islandR[i], 2) + Math.pow(islandC[j] - islandC[i], 2));

                            // 최솟값 갱신
                            if (tmp < min) {
                                min = tmp;
                                isle = j;
                            }
                        }
                    }
                }
            }

            nodes[isle] = true;
            res += min;
            count++;
        }

        res = Math.round(res * tax);
    }
}
