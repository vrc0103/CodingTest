import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int num;
    static int max_req;
    static int budget;
    static int[] request;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        num = Integer.parseInt(br.readLine().trim());

        request = new int[num];
        max_req = 0;

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            request[i] = Integer.parseInt(st.nextToken());
            max_req = Math.max(max_req, request[i]);
        }

        budget = Integer.parseInt(br.readLine().trim());
    }

    static void getRes() {
        int left = 0;
        int right = max_req;
        int mid;
        long sum;

        while (left <= right) {
            mid = (left + right) / 2;
            sum = 0;

            // 지방의 요청 예산이 기준보다 작으면 전부 지급하고, 크면 기준만큼만 지급하여 누적액 계산
            for (int i = 0; i < num; i++) {
                if (request[i] > mid) {
                    sum += mid;
                } else {
                    sum += request[i];
                }
            }

            // 누적 지급액이 총액보다 적으면 예산 기준치 증가, 크면 감소
            if (sum <= budget) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        res = right;
    }
}
