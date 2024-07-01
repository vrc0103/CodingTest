/*
 * 첫 줄에 정수의 갯수 N
 * N개의 숫자를 입력받아 중간값 구하기
 */

import java.io.*;
import java.util.*;

public class SWEA2063 {
    public static void main(String[] args) throws IOException {
        int nInput;
        int[] nArr;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()); // 첫줄
        nInput = Integer.parseInt(st.nextToken());
        nArr = new int[nInput];

        st = new StringTokenizer(bf.readLine()); // 둘째줄
        for (int i = 0; i < nInput; i++) {
            nArr[i] = Integer.parseInt(st.nextToken()); // 각 값을 입력받아 배열에 저장
        }
        Arrays.sort(nArr);
        System.out.print(nArr[nInput / 2]);
    }
}
