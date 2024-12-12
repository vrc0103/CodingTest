import java.io.*;
import java.util.*;

public class BOJ7569 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int countZero = 0;
    static int width, length, height;
    static int[][][] tomato;
    static Queue<int[]> queue = new LinkedList<>();

    // 동서남북 위 아래
    static int[] dR = { 0, 0, 1, -1, 0, 0 };
    static int[] dC = { 1, -1, 0, 0, 0, 0 };
    static int[] dH = { 0, 0, 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        width = Integer.parseInt(st.nextToken()); // col
        length = Integer.parseInt(st.nextToken()); // row
        height = Integer.parseInt(st.nextToken());

        // 토마토 상자 정보, 익은 토마토 queue에 추가
        tomato = new int[height][length][width];
        for (int h = 0; h < height; h++) {
            for (int l = 0; l < length; l++) {
                st = new StringTokenizer(br.readLine().trim());

                for (int w = 0; w < width; w++) {
                    tomato[h][l][w] = Integer.parseInt(st.nextToken());

                    if (tomato[h][l][w] == 1) {
                        queue.add(new int[] { h, l, w });
                    } else if (tomato[h][l][w] == 0) {
                        countZero++;
                    }
                }
            }
        }
    }

    static void getRes() {
        // 처음부터 모두 익은 토마토인 경우
        if (countZero == 0) {
            return;
        }

        int nowR, nowC, nowH;
        int nextR, nextC, nextH;
        int numOfNew = queue.size();
        int[] now;

        while (!queue.isEmpty() && countZero > 0) {
            res++;

            for (int i = 0; i < numOfNew; i++) {
                now = queue.remove();
                nowH = now[0];
                nowR = now[1];
                nowC = now[2];

                for (int j = 0; j < 6; j++) {
                    nextR = nowR + dR[j];
                    nextC = nowC + dC[j];
                    nextH = nowH + dH[j];

                    // 상자 범위 밖 처리
                    if (nextR < 0 || nextR >= length || nextC < 0 || nextC >= width || nextH < 0 || nextH >= height) {
                        continue;
                    }

                    if (tomato[nextH][nextR][nextC] == 0) {
                        tomato[nextH][nextR][nextC] = 1;
                        queue.add(new int[] { nextH, nextR, nextC });
                        countZero--;
                    }
                }
            }

            numOfNew = queue.size();
        }

        if (countZero > 0) {
            res = -1;
        }
    }
}
