import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String input;
    static int cnt0, cnt1;
    static int[] num;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(sb.toString());
    }

    static void setInfo() throws IOException {
        int len;

        input = br.readLine().trim();
        len = input.length();
        num = new int[len];

        cnt0 = cnt1 = 0;

        for (int i = 0; i < len; i++) {
            num[i] = input.charAt(i) - '0';

            if (num[i] == 0) {
                cnt0++;
            } else {
                cnt1++;
            }
        }
    }

    static void getRes() {
        cnt0 /= 2;
        cnt1 /= 2;

        for (int i = 0; i < num.length; i++) {
            // 숫자가 0이면 앞에서부터 반만 문자열에 추가
            if (num[i] == 0 && cnt0 > 0) {
                sb.append(num[i]);
                cnt0--;
            }

            // 숫자가 1
            if (num[i] == 1) {
                // 앞에서부터 반만큼 타노스
                if (cnt1 > 0) {
                    cnt1--;
                }

                // 나머지는 문자열에 추가
                else {
                    sb.append(num[i]);
                }
            }
        }
    }
}
