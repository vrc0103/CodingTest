/*
 * 톱니에 N/S극 존재
 * 회전 시 맞닿은 톱니가 다른 극이면 같이 돌아감
 */

import java.io.*;
import java.util.*;

public class BOJ14891 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] gears;
    static int res;

    public static void main(String[] args) throws IOException {
        getGear();

        getRes();

        System.out.println(res);
    }

    static void getGear() throws IOException {
        String input;

        gears = new int[4][8];

        for (int i = 0; i < 4; i++) {
            input = br.readLine();

            for (int j = 0; j < 8; j++) {
                gears[i][j] = input.charAt(j) - '0';
            }
        }
    }

    static void getRes() throws IOException {
        int count = Integer.parseInt(br.readLine());
        int num, dir;
        int tmp;
        int[] rot;

        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken());

            rot = new int[4];
            rot[num] = dir;

            // 왼쪽 톱니 확인
            for (int j = num; j > 0; j--) {
                if (gears[j][6] != gears[j - 1][2]) { // 맞닿은 극이 다른 경우
                    rot[j - 1] = rot[j] * -1;
                } else {
                    break;
                }
            }

            // 오른쪽 톱니 확인
            for (int j = num; j < 3; j++) {
                if (gears[j][2] != gears[j + 1][6]) { // 맞닿은 극이 다른 경우
                    rot[j + 1] = rot[j] * -1;
                } else {
                    break;
                }
            }

            // 톱니 회전
            for (int j = 0; j < 4; j++) {
                if (rot[j] == 1) { // 시계 방향
                    tmp = gears[j][7];
                    for (int k = 6; k >= 0; k--) {
                        gears[j][k + 1] = gears[j][k];
                    }
                    gears[j][0] = tmp;
                } else if (rot[j] == -1) { // 반시계 방향
                    tmp = gears[j][0];
                    for (int k = 1; k < 8; k++) {
                        gears[j][k - 1] = gears[j][k];
                    }
                    gears[j][7] = tmp;
                } else {
                    continue;
                }
            }
        }

        res = 0;
        for (int i = 0; i < 4; i++) {
            res += gears[i][0] * Math.pow(2, i);
        }
    }
}
