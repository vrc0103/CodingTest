import java.io.*;
import java.util.*;

public class BOJ11399 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int num;
    static int res;
    static int[] time;

    public static void main(String[] args) throws IOException {
        getMinTime();

        System.out.println(res);
    }

    static void getMinTime() throws IOException {
        res = 0;
        num = Integer.parseInt(br.readLine());
        time = new int[num];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);

        for (int i = 0; i < num; i++) {
            res += time[i] * (num - i);
        }
    }
}
