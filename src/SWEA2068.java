//10개의 수를 입력 받아, 그 중에서 가장 큰 수를 출력하는 프로그램
//첫 숫자는 테스트 케이스 갯수 T
//이후로 10개씩 입력받음

import java.io.*;
import java.util.*;

public class SWEA2068 {
    public static void main(String[] args) throws IOException {
        int T;
        int[][] Input;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(bf.readLine()); // 테스트 케이스 갯수
                                             // readLine은 줄마다 읽어온다 -> 각 줄마다 new StringTokenizer(bf.readLine());
        Input = new int[T][10];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine()); // 다음 줄 읽어옴
            for (int j = 0; j < 10; j++) {
                Input[i][j] = Integer.parseInt(st.nextToken()); // 각 줄마다 값 저장
            }
            Arrays.sort(Input[i]); // 각 행별로 정렬
        }

        for (int i = 0; i < T; i++) {
            System.out.println("#" + (i + 1) + " " + Input[i][9]);
        }
    }
}
