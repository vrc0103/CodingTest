import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int size;
    static int[] tang;

    public static void main(String[] args) throws Exception {
        int[] sum;
        int[][] count;

        size = Integer.parseInt(br.readLine().trim());
        tang = new int[size + 1];
        sum = new int[size + 1];
        count = new int[size + 1][6];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= size; i++) {
            tang[i] = Integer.parseInt(st.nextToken());

            /*
             * count 배열
             * 0, 3 : 과일 종류, 3번 인덱스의 과일이 마지막에 등장한 과일
             * 1, 4 : 해당 과일의 총합
             * 2, 5 : 해당 과일의 연속 개수
             */
            // 과일 2종류는 유지되지만 연결 끊김
            if (tang[i] == count[i - 1][0]) {
                count[i][0] = count[i - 1][3];
                count[i][1] = count[i - 1][4];
                count[i][2] = count[i - 1][5];
                count[i][3] = count[i - 1][0];
                count[i][4] = count[i - 1][1] + 1;
                count[i][5] = 1;
            }
            // 2종류 유지, 연결 O
            else if (tang[i] == count[i - 1][3]) {
                count[i][0] = count[i - 1][0];
                count[i][1] = count[i - 1][1];
                count[i][2] = count[i - 1][2];
                count[i][3] = count[i - 1][3];
                count[i][4] = count[i - 1][4] + 1;
                count[i][5] = count[i - 1][5] + 1;
            }
            // 새로운 과일
            else {
                count[i][0] = count[i - 1][3];
                count[i][1] = count[i - 1][5];
                count[i][2] = count[i - 1][5];
                count[i][3] = tang[i];
                count[i][4] = 1;
                count[i][5] = 1;
            }

            sum[i] = count[i][1] + count[i][4];
        }

        Arrays.sort(sum);

        System.out.println(sum[size]);
    }
}
