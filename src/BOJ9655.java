import java.io.*;

public class BOJ9655 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String res;
    static int num;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    public static void getRes() throws IOException {
        int turn;
        int remain;

        num = Integer.parseInt(br.readLine().trim());

        // 1개 또는 3개 가져감 -> (3n+1)번째 돌은 항상 번갈아가며 선택하게 됨 -> 3으로 나눠도 무방함
        // 나머지 돌은 1개씩만 가져갈 수 있음
        turn = num / 3;
        remain = num % 3;

        turn += remain;

        if (turn % 2 == 1) {
            res = "SK";
        } else {
            res = "CY";
        }
    }
}
