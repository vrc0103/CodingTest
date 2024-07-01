/*
 * 자연수 a, b는 1 ~ 9 사이
 * 사칙연산은 +, -, *, / 순서로 출력
 * 나누기는 소수점 이하 버림
 */

import java.util.*;

public class SWEA1938 {
    public static void main(String[] args){
        int a, b;
        int sum, sub, prdt, div;
        Scanner sc = new Scanner(System.in);

        a = sc.nextInt();
        b = sc.nextInt();

        sum = a + b;
        sub = a - b;
        prdt = a * b;
        div = a / b;

        System.out.println(sum + "\n" + sub + "\n" + prdt + "\n" + div);

        sc.close();
    }
}
