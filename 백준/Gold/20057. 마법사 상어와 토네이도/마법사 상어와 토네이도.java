import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int size;
    static int res;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        getArr();

        getSand();

        System.out.println(res);
    }

    static void getArr() throws IOException {
        size = Integer.parseInt(br.readLine());
        arr = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getSand() {
        int nowR = size / 2;
        int nowC = size / 2;
        int nextR, nextC;
        int len = 0;
        int dir = 0;
        int moved, sum;
        int[] torR = { 0, 1, 0, -1 };
        int[] torC = { -1, 0, 1, 0 };
        int[] sand1 = { -2, -1, -1, -1, 0, 1, 1, 1, 2 };
        int[] sand2 = { 0, -1, 0, 1, -2, -1, 0, 1, 0 };
        double[] percent = { 0.02, 0.1, 0.07, 0.01, 0.05, 0.1, 0.07, 0.01, 0.02 };

        res = 0;

        while (!(nowR == 0 && nowC == 0)) {
            // 토네이도 진행 방향이 2번 바뀔 때마다 이동 거리 1 증가
            if (dir % 2 == 0) {
                len++;
            }

            // 0, 3과 1, 2의 순서가 동일 (sand1 <-> sand2만 바뀜)
            // 1, 3에서 sand2의 방향 변경 필요
            else {
                for (int i = 0; i < sand2.length; i++) {
                    sand2[i] *= -1;
                }
            }

            // 마지막 이동에서 인덱스 에러 방지
            len = Math.min(len, size - 1);

            // 토네이도 이동
            for (int mv = 0; mv < len; mv++) {
                sum = 0;
                nowR = nowR + torR[dir];
                nowC = nowC + torC[dir];

                // 가로 방향 진행 : row가 sand1, col이 sand2
                if (dir % 2 == 0) {
                    for (int i = 0; i < percent.length; i++) {
                        nextR = nowR + sand1[i];
                        nextC = nowC + sand2[i];

                        moved = (int) (arr[nowR][nowC] * percent[i]);
                        sum += moved;

                        // 격자 밖의 모래
                        if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                            res += moved;
                            continue;
                        }

                        arr[nextR][nextC] += moved;
                    }
                }

                // 세로 방향 진행 : row가 sand2, col이 sand1
                else {
                    for (int i = 0; i < percent.length; i++) {
                        nextR = nowR + sand2[i];
                        nextC = nowC + sand1[i];

                        moved = (int) (arr[nowR][nowC] * percent[i]);
                        sum += moved;

                        // 격자 밖의 모래
                        if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                            res += moved;
                            continue;
                        }

                        arr[nextR][nextC] += moved;
                    }
                }

                // alpha 계산
                nextR = nowR + torR[dir];
                nextC = nowC + torC[dir];
                moved = arr[nowR][nowC] - sum;

                if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                    res += moved;
                } else {
                    arr[nextR][nextC] += moved;
                }

                arr[nowR][nowC] = 0;
            }

            dir = (dir + 1) % 4;
        }
    }
}
