import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[] height;
    static boolean[] selected;
    static boolean finded;

    public static void main(String[] args) throws IOException {
        getDwarf();

        System.out.print(sb.toString());
    }

    static void getDwarf() throws IOException {
        height = new int[9];
        selected = new boolean[9];

        for (int i = 0; i < 9; i++) {
            height[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(height);

        finded = false;
        comb(0);
    }

    static void comb(int cnt) {
        // 정답 찾으면 탐색 X
        if (finded) {
            return;
        }

        if (cnt == 7) {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                if (selected[i]) {
                    sum += height[i];
                }
            }

            // 키 합이 100이면 출력
            if (sum == 100) {
                for (int i = 0; i < 9; i++) {
                    if (selected[i]) {
                        sb.append(height[i]).append("\n");
                    }
                }

                finded = true;
            }

            return;
        }

        // 조합 선택
        for (int i = cnt; i < 9; i++) {
            if (!selected[i]) {
                selected[i] = true;
                comb(cnt + 1);
                selected[i] = false;
            }
        }
    }
}
