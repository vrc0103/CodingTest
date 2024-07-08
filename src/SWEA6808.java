/*
 * 1 ~ 18 카드, 9장씩 나눔
 * 높은 숫자를 낸 사람이 두 카드 숫자의 총합만큼 점수 획득
 * 규영이가 낼 카드의 순서가 입력됨
 * 규영이가 이기는 경우와 지는 경우의 갯수 출력
 */

import java.io.*;
import java.util.*;

public class SWEA6808 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int pointGY, pointIY;
    static int win, lose;
    static int[] cardGY, cardIY, cardTmp;
    static boolean[] selected;

    static void getCardArr() throws IOException {
        int idx = 0;
        boolean chk;

        cardGY = new int[9];
        cardIY = new int[9];
        cardTmp = new int[9];
        selected = new boolean[9];

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < 9; i++) { // 규영이 카드 순서
            cardGY[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= 18; i++) { // 인영이 카드뭉치(순서X)
            chk = true;

            for (int j = 0; j < 9; j++) { // 규영이 카드뭉치에 있는 숫자는 제외
                if (i == cardGY[j]) {
                    chk = false;
                }
            }

            if (chk) {
                cardTmp[idx++] = i;
            }
        }
    }

    static void getCount(int depth) {
        if (depth == 9) { // 인영이 카드 순서가 결정되면 점수 계산
            pointGY = 0;
            pointIY = 0;

            for (int i = 0; i < 9; i++) {
                if (cardGY[i] > cardIY[i]) {
                    pointGY += cardGY[i] + cardIY[i];
                } else {
                    pointIY += cardGY[i] + cardIY[i];
                }
            }

            if (pointGY > pointIY) {
                win++;
            } else if (pointGY < pointIY) {
                lose++;
            }

            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!selected[i]) { // 아직 선택하지 않은 카드 중에서 하나 선택
                selected[i] = true;
                cardIY[depth] = cardTmp[i];
                getCount(depth + 1);
                selected[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            win = lose = 0;

            getCardArr();

            getCount(0);

            sb.append(String.format("#%d %d %d\n", tc, win, lose));
        }

        System.out.print(sb);
    }
}
