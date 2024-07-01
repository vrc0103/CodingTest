//첫 줄에 케이스 갯수 T
//2 개의 수 a, b를 입력받아 a/b의 몫과 나머지 출력

import java.util.*;
import java.io.*;

public class SWEA2029 {
    public static void main(String[] args) throws IOException {
        int T;
        int a, b;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()); // 첫 줄 선택
        T = Integer.parseInt(st.nextToken()); // 정수로 변환해 T에 저장

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine()); // 다음 줄 선택
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            System.out.println("#" + (i + 1) + " " + a / b + " " + a % b);
        }
    }
}
