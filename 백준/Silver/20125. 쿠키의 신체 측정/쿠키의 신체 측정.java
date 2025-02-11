import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int size;
    static boolean[][] map;
    static int[] head;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.print(sb.toString());
    }

    static void setInfo() throws IOException {
        String input;

        size = Integer.parseInt(br.readLine().trim());

        map = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            input = br.readLine().trim();

            for (int j = 0; j < size; j++) {
                if (input.charAt(j) == '*') {
                    map[i][j] = true;

                    // 처음 입력되는 *이 머리 위치
                    if (head == null) {
                        head = new int[] { i, j };
                    }
                }
            }
        }
    }

    static void getRes() {
        int[] heart;
        int[] lastBody = new int[2];
        int[] len = new int[5]; // 왼팔, 오른팔, 허리, 왼다리, 오른다리

        heart = new int[] { head[0] + 1, head[1] };

        for (int i = 1; i < size; i++) {
            // 왼팔 길이
            if (heart[1] - i >= 0 && map[heart[0]][heart[1] - i]) {
                len[0]++;
            }

            // 오른팔 길이
            if (heart[1] + i < size && map[heart[0]][heart[1] + i]) {
                len[1]++;
            }

            // 허리 길이
            if (heart[0] + i < size && map[heart[0] + i][heart[1]]) {
                len[2]++;
            }
        }

        lastBody = new int[] { heart[0] + len[2], heart[1] };

        for (int i = lastBody[0] + 1; i < size; i++) {
            // 왼다리 길이
            if (map[i][lastBody[1] - 1]) {
                len[3]++;
            }

            // 오른다리 길이
            if (map[i][lastBody[1] + 1]) {
                len[4]++;
            }
        }

        sb.append(String.format("%d %d\n", heart[0] + 1, heart[1] + 1)); // (1, 1) ~ (N, N)
        for (int i = 0; i < 5; i++) {
            sb.append(len[i]).append(" ");
        }
        sb.append("\n");
    }
}
