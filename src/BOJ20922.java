import java.io.*;
import java.util.*;

public class BOJ20922 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int len, num;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        len = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());

        arr = new int[len];
        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int[] cnt = new int[100001];
        int start = 0;

        // 투 포인터 - end = i
        for (int i = 0; i < len; i++) {
            cnt[arr[i]]++;

            if (cnt[arr[i]] > num) {
                // 현재 연속 부분 수열의 길이와 비교하여 최댓값 갱신
                res = Math.max(res, (i - 1) - start + 1);

                // 앞에서부터 조건에 맞을 때까지 숫자 제거
                while (cnt[arr[i]] > num) {
                    cnt[arr[start]]--;
                    start++;
                }
            }

            if (i == len - 1) {
                res = Math.max(res, i - start + 1);
            }
        }
    }
}
