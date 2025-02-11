import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int size, coin;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        coin = Integer.parseInt(st.nextToken());

        // 코인 개수가 전체 칸의 절반 미만이면 절대 불가능
        if (coin < size * size / 2) {
            System.out.println(-1);

            return;
        }

        boolean judge;

        judge = getRes(1);

        // 맵 크기가 홀수이면 다른 배치로 재시도
        if (!judge && size % 2 == 1) {
            judge = getRes(0);
        }

        if (!judge) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.print(sb);
        }
    }

    static boolean getRes(int idx) {
        int count = coin / (size * size) - 1;
        int coinTmp = coin;

        map = new int[size][size];

        // 일단 map에 동일한 개수만큼 코인 최대한 배치
        for (int i = 0; i < size; i++) {
            Arrays.fill(map[i], count);
        }

        coinTmp -= count * size * size;
        count = 1;

        loop: while (true) {
            // 1칸 간격으로 count만큼 코인 배치
            // 첫 사이클에서는 1개씩, 이후로는 2개씩 사용
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if ((i + j) % 2 == idx) {
                        map[i][j] += count;
                        coinTmp -= count;
                    }

                    if (coinTmp == 0) {
                        break loop;
                    }
                }
            }

            // 남은 코인이 홀수개이면 옆 칸과 차이가 2인 칸 발생
            if (coinTmp % 2 == 1) {
                return false;
            }

            // 다음 배치할 코인 개수를 2로 늘리고, 코인을 배치할 칸을 조정
            idx = (idx + 1) % 2;
            count = 2;
        }

        return true;
    }
}