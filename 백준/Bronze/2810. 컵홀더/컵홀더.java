import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res;
    static int num;
    static String input;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int cnt;
        int len;
        char seat;

        num = Integer.parseInt(br.readLine().trim());
        input = br.readLine().trim();
        len = input.length();
        cnt = 1; // 맨 왼쪽 컵홀더

        for (int i = 0; i < len; i++) {
            seat = input.charAt(i);

            // 일반석 : 오른쪽에 컵홀더 존재
            if (seat == 'S') {
                cnt++;
            }
            // 커플석 : 다음 커플석의 오른쪽에 컵홀더 존재
            else {
                i++;
                cnt++;
            }
        }

        // 좌석 수와 컵홀더 수 중 작은값
        res = Math.min(num, cnt);
    }
}
