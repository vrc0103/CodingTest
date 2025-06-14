import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static double res;
    static int angle;
    static long[][] point;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.printf("%.1f\n", res);
    }

    static void setInfo() throws Exception {
        angle = Integer.parseInt(br.readLine().trim());
        point = new long[angle][2];

        for (int i = 0; i < angle; i++) {
            st = new StringTokenizer(br.readLine().trim());
            point[i][0] = Long.parseLong(st.nextToken());
            point[i][1] = Long.parseLong(st.nextToken());
        }
    }

    static void getRes() {
        long[] pivot = point[0];

        for (int i = 1; i < angle - 1; i++) {
            long[] point1 = point[i];
            long[] point2 = point[i + 1];

            long area = ((pivot[0] * point1[1] + point1[0] * point2[1] + point2[0] * pivot[1])
                    - (pivot[1] * point1[0] + point1[1] * point2[0] + point2[1] * pivot[0]));

            res += area;
        }

        res = Math.abs(res) / 2;
    }
}
