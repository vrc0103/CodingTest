import java.io.*;
import java.util.*;

/*
 * 사다리 타기
 * X,Y 좌표(= 열, 행 반대) : [0][0]  [1][0]  ...
 * 사다리는 1, 도착지점은 2
 * 도착 지점을 선택하게 되는 사다리의 X좌표
 */

public class SWEA1210 {
    public static int findX(int[][] nLadder) {
        int nY = 99;
        int nRes = 0;

        for (int i = 0; i < 100; i++) { // 당첨 위치의 X좌표 찾기
            if (nLadder[i][99] == 2) {
                nRes = i;
            }
        }

        while (nY != 0) {
            if (nRes != 0 && nLadder[nRes - 1][nY] == 1) { // 왼쪽으로 길이 있는 경우
                while (nRes != 0 && nLadder[nRes - 1][nY] == 1) { // 길이 끊어질 때까지 왼쪽으로 이동
                    nRes--;
                }
            } else if (nRes != 99 && nLadder[nRes + 1][nY] == 1) { // 오른쪽으로 길이 있는 경우
                while (nRes != 99 && nLadder[nRes + 1][nY] == 1) { // 길이 끊어질 때까지 오른쪽으로 이동
                    nRes++;
                }
            }
            nY--;
        }

        return nRes;
    }

    public static void main(String[] args) throws IOException {
        int[][] nLadder = new int[100][100];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        for (int test = 1; test <= 10; test++) {

            sb = new StringBuilder();
            sb.append("#").append(br.readLine().trim()).append(" ");

            for (int i = 0; i < 100; i++) { // 사다리 구현
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < 100; j++) {
                    nLadder[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(findX(nLadder));
            System.out.println(sb);
        }
    }
}
