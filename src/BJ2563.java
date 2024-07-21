/*
 * 100 x 100 도화지에 10x 10 색종이를 붙여서 색종이가 차지하는 부분의 넓이를 구해야 함
 * 색종이의 왼쪽 아래 x, y 좌표가 입력됨
 *   -> 색종이 : (x, y) ~ (x + 10, y + 10)
 * 색종이가 겹치는 범위를 고려해야 하므로 boolean을 이용하여 색종이 영역 선택
 */

import java.io.*;
import java.util.*;

public class BJ2563 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int num;
    static int paperX, paperY;
    static int area;
    static boolean[][] space;

    public static void main(String[] args) throws IOException {
        getArea();

        System.out.println(area);
    }

    static void getArea() throws IOException {
        area = 0;
        space = new boolean[100][100];

        num = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            paperX = Integer.parseInt(st.nextToken());
            paperY = Integer.parseInt(st.nextToken());

            for (int x = paperX; x < paperX + 10; x++) { // x, y 좌표를 편의상 row, col 좌표로 계산
                for (int y = paperY; y < paperY + 10; y++) {
                    if (!space[x][y]) {
                        space[x][y] = true;
                        area++;
                    }
                }
            }
        }
    }
}
