import java.io.*;
import java.util.*;

public class SWEA1952 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int[] price; // 일일, 1개월, 3개월, 1년 순서
    static int[] plan; // 이용 계획
    static int res;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            getInformation();

            for (int i = 0; i < 3; i++) {
                getPrice(0, i, 0);
            }

            sb.append(String.format("#%d %d\n", tc, res));
        }

        System.out.print(sb);
    }

    static void getInformation() throws IOException {
        price = new int[4];
        plan = new int[12];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        res = price[3]; // 연간권으로 초기화

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 12; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getPrice(int month, int type, int pay) {
        if (month >= 12) {
            res = Math.min(res, pay);
            return;
        }

        switch (type) {
            case 0: // 일일권
                pay += plan[month] * price[0];
                break;
            case 1: // 1개월권
                pay += price[1];
                break;
            case 2: // 3개월권
                pay += price[2];
                month += 2;
                break;
        }

        for (int i = 0; i < 3; i++) {
            getPrice(month + 1, i, pay);
        }
    }
}
