import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res;
    static int[] point;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int sub1, sub2;

        point = new int[11];

        // 누적합
        for (int i = 1; i < 11; i++) {
            point[i] = point[i - 1] + Integer.parseInt(br.readLine().trim());

            // 100에 가장 가까운 값 선택, 차이가 같으면 큰 값으로 갱신
            sub1 = Math.abs(100 - point[i - 1]);
            sub2 = Math.abs(100 - point[i]);

            if (sub1 >= sub2) {
                res = point[i];
            }
        }
    }
}
