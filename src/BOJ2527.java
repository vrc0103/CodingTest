/*
 * 입력 : x y p q
 * 가로 : x ~ p (x < p)
 * 세로 : y ~ q (y < q)
 * 중복영역 : 면(a), 선(b), 점(c), 없음(d)
 */

import java.io.*;
import java.util.*;

public class BOJ2527 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        getJudge();

        System.out.print(sb.toString());
    }

    static void getJudge() throws IOException {
        int x1, y1, p1, q1;
        int x2, y2, p2, q2;

        for (int tc = 0; tc < 4; tc++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            p1 = Integer.parseInt(st.nextToken());
            q1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            p2 = Integer.parseInt(st.nextToken());
            q2 = Integer.parseInt(st.nextToken());

            // 겹치지 않게 놓인 경우
            if (x1 > p2 || x2 > p1 || y1 > q2 || y2 > q1) {
                sb.append("d\n");
            }

            // 점으로 겹침
            else if ((x1 == p2 && (y1 == q2 || q1 == y2)) || (p1 == x2 && (y1 == q2 || q1 == y2))) {
                sb.append("c\n");
            }

            // 선으로 겹침
            else if (x1 == p2 || p1 == x2 || y1 == q2 || q1 == y2) {
                sb.append("b\n");
            }

            // 위 조건을 모두 통과하면 면으로 겹치는 것으로 판단
            else {
                sb.append("a\n");
            }
        }
    }
}
