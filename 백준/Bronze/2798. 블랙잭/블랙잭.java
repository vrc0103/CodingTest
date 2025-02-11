import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int num, max;
    static int[] card;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        setCard();

        getMax(0, 0);

        System.out.println(res);
    }

    static void setCard() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        card = new int[num];
        selected = new boolean[num];

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < num; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        res = 0;
    }

    static void getMax(int cnt, int sum) {
        if (cnt == 3) {
            res = Math.max(res, sum);

            return;
        }

        // 조합
        int val;

        for (int i = cnt; i < num; i++) {
            val = sum + card[i];

            // 딜러가 선언한 값보다 작은 경우에만 조합에 추가
            if (!selected[i] && val <= max) {
                selected[i] = true;
                getMax(cnt + 1, val);
                selected[i] = false;
            }
        }
    }
}
