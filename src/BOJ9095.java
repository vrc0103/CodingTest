import java.io.*;

public class BOJ9095 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int input;
    static int[] count;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            getCount();
        }

        System.out.print(sb.toString());
    }

    static void getCount() throws IOException {
        int size;

        input = Integer.parseInt(br.readLine().trim());

        // 초기화하기 쉽게 최소 3까지 생성
        size = Math.max(input, 3);
        count = new int[size + 1];

        count[1] = 1;
        count[2] = 2;
        count[3] = 4;

        for (int i = 4; i <= input; i++) {
            // 1, 2, 3만으로 표현
            // -> i = 1 + (i - 1) = 2 + (i - 2) = 3 + (i - 3)
            count[i] = count[i - 1] + count[i - 2] + count[i - 3];
        }

        sb.append(count[input]).append("\n");
    }
}
