import java.io.*;
import java.util.*;

public class BJ7576 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int row, col;
    static int[][] box;

    static int numOfOne;
    static int[] dirR = { 0, 1, 0, -1 };
    static int[] dirC = { 1, 0, -1, 0 };
    static Queue<Integer> arrR, arrC; // ArrayList로 계속 쌓기만 하면 시간 초과 걸림 -> Queue로 확인한 위치는 제거하며 탐색
    static boolean ifAllOne;

    public static void main(String[] args) throws IOException {
        getBox();

        if (ifAllOne) {
            sb.append("0");
        } else {
            countDate();
        }

        System.out.println(sb);
    }

    static void getBox() throws IOException {
        ifAllOne = true;
        st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        arrR = new LinkedList<>();
        arrC = new LinkedList<>();

        // 토마토 초기화
        box = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < col; c++) {
                box[r][c] = Integer.parseInt(st.nextToken());

                if (box[r][c] == 0) { // 모두 익은 채로 들어오는지 확인
                    ifAllOne = false;
                }

                if (box[r][c] == 1) { // 익은 토마토의 위치
                    arrR.add(r);
                    arrC.add(c);
                }
            }
        }
    }

    static void countDate() {
        int locR, locC;
        int count = 0;

        while (true) {
            numOfOne = arrR.size(); // 토마토 갱신 전 갯수

            for (int i = 0; i < numOfOne; i++) { // 익은 토마토에 인접한 토마토를 1로 변경
                for (int j = 0; j < 4; j++) {
                    locR = arrR.peek() + dirR[j];
                    locC = arrC.peek() + dirC[j];

                    if (locR >= 0 && locR < row && locC >= 0 && locC < col && box[locR][locC] == 0) {
                        box[locR][locC] = 1;
                        arrR.add(locR);
                        arrC.add(locC);
                    }
                }
                arrR.remove();
                arrC.remove();
            }

            if (arrR.size() == 0) { // 새로 익은 토마토가 없는 경우 반복문 탈출
                break;
            }

            count++;
        }

        for (int r = 0; r < row; r++) { // 다 익었는지 확인
            for (int c = 0; c < col; c++) {
                if (box[r][c] == 0) {
                    count = -1;
                    break;
                }
            }
        }

        sb.append(count);
    }
}
