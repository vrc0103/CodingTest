import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long res = 0;
        int num, target;
        long left = 1, right = 0, mid = 0;
        long[] lan;

        // 정보
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        lan = new long[num];
        for(int i = 0; i < num; i++) {
            lan[i] = Integer.parseInt(br.readLine().trim());
            right = Math.max(right, lan[i]);
        }

        // 풀이
        // 해당 개수가 계산될 때까지 투포인터로 이분탐색
        while(left <= right) {
            mid = (left + right) / 2;
            int cnt = 0;

            for(long n : lan) {
                cnt += n / mid;
            }
            // System.out.printf("L = %d  R = %d  M = %d  C = %d\n", left, right, mid, cnt);

            // 개수 부족 -> 더 작게 잘라야 함
            if(cnt < target) {
                right = mid - 1;
            }
            // 개수 초과 -> 더 크게 잘라도 됨
            else {
                res = mid;
                left = mid + 1;
            }
        }

        System.out.println(res);
    }
}
