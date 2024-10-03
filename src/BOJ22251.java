import java.io.*;
import java.util.*;

public class BOJ22251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int maxFloor, maxDigit, maxFlip, nowFloor;

    static int[][] display = {
            { 1, 1, 1, 1, 1, 1, 0 }, // 0
            { 0, 0, 0, 0, 1, 1, 0 }, // 1
            { 1, 0, 1, 1, 0, 1, 1 }, // 2
            { 1, 0, 0, 1, 1, 1, 1 }, // 3
            { 0, 1, 0, 0, 1, 1, 1 }, // 4
            { 1, 1, 0, 1, 1, 0, 1 }, // 5
            { 1, 1, 1, 1, 1, 0, 1 }, // 6
            { 1, 0, 0, 0, 1, 1, 0 }, // 7
            { 1, 1, 1, 1, 1, 1, 1 }, // 8
            { 1, 1, 0, 1, 1, 1, 1 } }; // 9

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        maxFloor = Integer.parseInt(st.nextToken());
        maxDigit = Integer.parseInt(st.nextToken());
        maxFlip = Integer.parseInt(st.nextToken());
        nowFloor = Integer.parseInt(st.nextToken());

        res = 0;
        dfs(0, 0, 0);
    }

    static void dfs(int flip, int digit, int fake) {
        // 모든 자릿수를 변경하면 재귀 탈출
        if (digit == maxDigit) {
            // 최대 층을 넘거나 0층이거나 원래 층과 같으면 계산X
            if (fake == 0 || fake == nowFloor || fake > maxFloor) {
                return;
            }

            res++;
            return;
        }

        int num = nowFloor / (int) Math.pow(10, digit) % 10;
        int sum;
        int fakeNum;

        for (int i = 0; i < 10; i++) {
            sum = 0;

            // 반전 횟수
            for (int j = 0; j < 7; j++) {
                if (display[num][j] != display[i][j]) {
                    sum++;
                }
            }

            fakeNum = fake + i * (int) Math.pow(10, digit);
            if (flip + sum <= maxFlip) {
                dfs(flip + sum, digit + 1, fakeNum);
            }
        }
    }
}
