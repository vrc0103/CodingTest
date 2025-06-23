import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size, len;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());

        map = new int[size][size];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        // 행 확인
        for (int r = 0; r < size; r++) {
            int prev = map[r][0];
            int cnt = 0;
            boolean pos = true;

            for (int c = 0; c < size; c++) {
                if (map[r][c] == prev) {
                    cnt++;
                } else if (map[r][c] - prev == 1) {
                    // 이전 높이보다 1칸 높은 경우
                    if (cnt - len < 0) {
                        // 경사 설치 불가
                        pos = false;
                        break;
                    }

                    prev = map[r][c];
                    cnt = 1;
                } else if (map[r][c] - prev == -1) {
                    // 이전에 경사로를 설치하고 있던 경우
                    if (cnt < 0) {
                        pos = false;
                        break;
                    }

                    prev = map[r][c];
                    cnt = len * -1 + 1;
                } else {
                    // 2칸 이상은 경사로 설치 불가
                    pos = false;
                    break;
                }
            }

            if (pos && cnt >= 0) {
                res++;
            }
        }

        // 열 확인
        for (int c = 0; c < size; c++) {
            int prev = map[0][c];
            int cnt = 0;
            boolean pos = true;

            for (int r = 0; r < size; r++) {
                if (map[r][c] == prev) {
                    cnt++;
                } else if (map[r][c] - prev == 1) {
                    // 이전 높이보다 1칸 높은 경우
                    if (cnt - len < 0) {
                        // 경사 설치 불가
                        pos = false;
                        break;
                    }

                    prev = map[r][c];
                    cnt = 1;
                } else if (map[r][c] - prev == -1) {
                    // 이전에 경사로를 설치하고 있던 경우
                    if (cnt < 0) {
                        pos = false;
                        break;
                    }

                    prev = map[r][c];
                    cnt = len * -1 + 1;
                } else {
                    // 2칸 이상은 경사로 설치 불가
                    pos = false;
                    break;
                }
            }

            if (pos && cnt >= 0) {
                res++;
            }
        }
    }
}
