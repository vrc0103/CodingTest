//터미널에는 안돌아가는데 제출은 성공함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class SWEA1230 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static int nTestCase = 10;
    public static int nEncLen, nInstLen; // 암호문 길이, 명령어 갯수
    public static int nStartLoc, nModLen; // 수정 시작 지점, 수정 길이
    public static char cMod; // 명령어 종류
    public static LinkedList<Integer> EncList;

    public static void ModifyEnc() {
        cMod = st.nextToken().charAt(0);

        switch (cMod) {
            case 'I':
                nStartLoc = Integer.parseInt(st.nextToken());
                nModLen = Integer.parseInt(st.nextToken());
                for (int idx = 0; idx < nModLen; idx++) {
                    EncList.add(nStartLoc + idx, Integer.parseInt(st.nextToken()));
                }
                break;
            case 'D':
                nStartLoc = Integer.parseInt(st.nextToken());
                nModLen = Integer.parseInt(st.nextToken());
                for (int idx = 0; idx < nModLen; idx++) {
                    EncList.remove(nStartLoc + 1); // 삭제하면 당겨지므로 같은 자리만 계속 삭제
                }
                break;
            case 'A':
                nModLen = Integer.parseInt(st.nextToken());
                for (int idx = 0; idx < nModLen; idx++) {
                    EncList.add(Integer.parseInt(st.nextToken()));
                }
                break;
        }

    }

    public static void main(String[] args) throws IOException {

        for (int tc = 1; tc <= nTestCase; tc++) {
            sb = new StringBuilder();
            EncList = new LinkedList<>();

            nEncLen = Integer.parseInt(br.readLine().trim()); // 암호문 길이
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < nEncLen; i++) { // 입력받은 암호문을 리스트에 저장
                EncList.add(Integer.parseInt(st.nextToken()));
            }

            nInstLen = Integer.parseInt(br.readLine().trim()); // 명령어 갯수
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < nInstLen; i++) {
                ModifyEnc();
            }

            sb.append(String.format("#%d", tc));
            for (int i = 0; i < 10; i++) {
                sb.append(String.format(" %d", EncList.get(i)));
            }
            System.out.println(sb);
        }
    }
}
