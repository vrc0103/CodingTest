/*
 * 남학생 : 1, 자기 번호의 배수 숫자인 스위치 변경
 * 여학생 : 2, 자기 번호를 기준으로 대칭이 되는 최대 스위치 변경
 * 비트 마스킹 연습해보기 : 스위치 갯수가 100개 이하 -> 범위 넘어감 (구현은 맞는듯?)
 */

import java.io.*;
import java.util.*;

public class BJ1244 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int numOfSwitch, numOfStudent;
    static int[] state;

    public static void main(String[] args) throws IOException {
        getSwitch();

        getRes();

        System.out.println(sb.toString().trim());
    }

    static void getSwitch() throws IOException {
        numOfSwitch = Integer.parseInt(br.readLine().trim());
        state = new int[numOfSwitch + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= numOfSwitch; i++) {
            state[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() throws IOException {
        int gender, num;

        numOfStudent = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < numOfStudent; i++) {
            st = new StringTokenizer(br.readLine().trim());
            gender = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());

            if (gender == 1) { // 남학생
                for (int j = 1; j * num <= numOfSwitch; j++) {
                    state[j * num] ^= 1;
                }
            } else { // 여학생
                state[num] ^= 1;

                for (int j = 1; num + j <= numOfSwitch && num - j > 0; j++) {
                    if (state[num + j] == state[num - j]) {
                        state[num + j] ^= 1;
                        state[num - j] ^= 1;
                    } else {
                        break;
                    }
                }
            }
        }

        for (int i = 1; i <= numOfSwitch; i++) {
            sb.append(state[i]);

            if (i % 20 == 0 && i != numOfSwitch) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
    }

    // static void BitMasking() throws IOException {
    // int num;
    // String tmp;

    // state = 1; // 자릿수 유지를 위한 기본값
    // numOfSwitch = Integer.parseInt(br.readLine().trim());

    // st = new StringTokenizer(br.readLine());

    // for (int i = 0; i < numOfSwitch; i++) {
    // state = (state << 1) + Integer.parseInt(st.nextToken()); // state를 비트 마스킹으로
    // 구현, 876...21 순서
    // }

    // numOfStudent = Integer.parseInt(br.readLine().trim());

    // for (int i = 0; i < numOfStudent; i++) {
    // st = new StringTokenizer(br.readLine().trim());
    // if (Integer.parseInt(st.nextToken()) == 1) { // 남학생
    // num = Integer.parseInt(st.nextToken());

    // for (int j = 1; numOfSwitch - j * num >= 0; j++) {
    // state = state ^ (1 << (numOfSwitch - j * num));
    // }
    // } else { // 여학생
    // num = Integer.parseInt(st.nextToken());
    // tmp = Integer.toBinaryString(state);

    // state = state ^ (1 << numOfSwitch - num);
    // for (int j = 1; num + j <= numOfSwitch && num - j > 0; j++) {
    // if (tmp.charAt(num + j) == tmp.charAt(num - j)) {
    // state = state ^ (1 << numOfSwitch - num + j);
    // state = state ^ (1 << numOfSwitch - num - j);
    // } else {
    // break;
    // }
    // }
    // }
    // }

    // tmp = Integer.toBinaryString(state);
    // for (int i = 1; i <= numOfSwitch; i++) {
    // sb.append(tmp.charAt(i)).append(" ");
    // }
    // }
}
