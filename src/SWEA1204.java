import java.io.*;
import java.util.*;

public class SWEA1204 {
    public static void main(String[] args) throws IOException {
        int nT;
        int nRes, nMax;
        int[] nScr = new int[101]; // 0 ~ 100점
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nMax = 0;
            nRes = 0;
            System.out.print("#" + br.readLine().trim() + " ");
            st = new StringTokenizer(br.readLine().trim());

            for (int i = 0; i < 101; i++) { // 점수 배열 초기화
                nScr[i] = 0;
            }
            for (int i = 0; i < 1000; i++) { // 해당 점수의 숫자 1 증가
                nScr[Integer.parseInt(st.nextToken())] += 1;
            }
            for (int i = 0; i < 101; i++) {
                if (nMax <= nScr[i]) { // 집계된 학생 수의 최댓값 갱신
                    nMax = nScr[i];
                    nRes = i; // 해당 점수로 갱신
                }
            }

            System.out.println(nRes);
        }
    }
}
