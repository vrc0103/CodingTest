import java.io.*;
import java.util.*;

public class SWEA1244 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static String input;
    static int count;
    static int[] num;
    static int max;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            sefInfo();

            getRes();

            sb.append(String.format("#%d %d\n", tc, max));
        }

        System.out.print(sb.toString());
    }

    static void sefInfo() throws IOException {
        int len;

        st = new StringTokenizer(br.readLine().trim());
        input = st.nextToken();
        count = Integer.parseInt(st.nextToken());

        len = input.length();
        num = new int[len];
        for (int i = 0; i < len; i++) {
            num[i] = input.charAt(i) - '0';
        }
    }

    static void getRes() {
        max = 0;

        DFS(0, 0);
    }

    static void DFS(int start, int depth) {
        int len = num.length;

        // 모든 교체 완료
        if (depth == count) {
            int tmpNum = 0;

            for (int i = 0; i < len; i++) {
                tmpNum = tmpNum * 10 + num[i];
            }

            max = Math.max(max, tmpNum);

            return;
        }

        int tmp;

        for (int i = start; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    continue;
                }

                // 교체
                tmp = num[j];
                num[j] = num[i];
                num[i] = tmp;

                DFS(i, depth + 1);

                // 원복
                tmp = num[j];
                num[j] = num[i];
                num[i] = tmp;
            }
        }
    }
}