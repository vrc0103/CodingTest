import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int num, max, cons, cpn;
    static int[] sushi;
    static int[] count;

    public static void main(String[] args) throws IOException {
        int sum = 0;

        // 문제 정보
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken()); // 회전초밥 칸 수
        max = Integer.parseInt(st.nextToken()); // 초밥 종류
        cons = Integer.parseInt(st.nextToken()); // 연속 개수
        cpn = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        sushi = new int[num];
        count = new int[max + 1];

        for (int i = 0; i < num; i++) {
            sushi[i] = Integer.parseInt(br.readLine().trim());

            // 최초 연속 개수까지 초밥 수 계산
            if (i < cons) {
                count[sushi[i]]++;

                // 처음 먹는 초밥
                if (count[sushi[i]] == 1) {
                    sum++;
                }
            }
        }

        // 풀이
        int end = cons - 1;
        int[] res = new int[num];

        res[0] = sum;

        // 쿠폰 스시 추가 : 연결되지 않아도 먹을 수 있으며 벨트에 없는 경우 새로 만들어 줌
        if (count[cpn] == 0) {
            res[0]++;
        }

        for (int i = 1; i < num; i++) {
            end = (cons - 1 + i) % num;

            count[sushi[i - 1]]--;
            if (count[sushi[i - 1]] == 0) {
                sum--;
            }

            count[sushi[end]]++;
            if (count[sushi[end]] == 1) {
                sum++;
            }

            res[i] = sum;

            if (count[cpn] == 0) {
                res[i]++;
            }
        }

        Arrays.sort(res);

        System.out.println(res[num - 1]);
    }
}
