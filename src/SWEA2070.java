/*
 * 첫 줄에 케이스 갯수 T 
 * 2개의 수를 입력받아 크기를 비교하고 등호, 부등호 출력
 */

import java.util.*;
import java.io.*;

public class SWEA2070 {
    public static void main(String[] args) throws IOException {
        int T;
        int a, b;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            System.out.print("#" + (i + 1) + " ");
            if (a > b)
                System.out.println(">");
            else if (a < b)
                System.out.println("<");
            else
                System.out.println("=");
        }
    }
}
