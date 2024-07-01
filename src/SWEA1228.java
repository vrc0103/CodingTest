/*
 * 1 : 암호문 길이
 * 2 : 암호문 원문
 * 3 : 명령어 갯수
 * 4 : 명령어
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class SWEA1228 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static int nTestCase = 10;
    public static int nEncLen; // 암호문 길이
    public static int nInstLen; // 명령어 갯수
    public static int nInsertLoc; // 입력할 위치
    public static int nInputLen; // 추가할 숫자 갯수
    public static List<Integer> EncArr; // 암호문 배열

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= nTestCase; tc++) {
            sb = new StringBuilder();
            EncArr = new ArrayList<>();

            nEncLen = Integer.parseInt(br.readLine().trim()); // 암호문 길이 입력
            st = new StringTokenizer(br.readLine().trim()); // 암호문 입력
            while (st.hasMoreElements()) {
                EncArr.add(Integer.parseInt(st.nextToken())); // 암호문을 리스트에 저장
            }

            nInstLen = Integer.parseInt(br.readLine().trim()); // 명령어 갯수
            st = new StringTokenizer(br.readLine().trim()); // 명령어 정보
            while (st.hasMoreElements()) { // 명령어에 따라 암호문 수정
                if (st.nextToken() == "I") { // I만 입력될 것이므로 무시
                    continue;
                } else {
                    nInsertLoc = Integer.parseInt(st.nextToken()); // 삽입 위치
                    nInputLen = Integer.parseInt(st.nextToken()); // 삽입할 숫자 갯수
                    for (int idx = 0; idx < nInputLen; idx++) {
                        EncArr.add(nInsertLoc + idx, Integer.parseInt(st.nextToken()));
                    }
                }
            }

            sb.append(String.format("#%d", tc));
            for (int i = 0; i < 10; i++) {
                sb.append(String.format(" %d", EncArr.get(i)));
            }
            System.out.println(sb);
        }
    }
}
