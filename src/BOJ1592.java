import java.io.*;
import java.util.*;

public class BOJ1592 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int num, max, len;
    static int res;
    static int[] count;

    public static void main(String[] args) throws IOException {
        getArr();

        System.out.println(res);
    }

    static void getArr() throws IOException {
        int loc;

        st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());

        res = 0;
        loc = 0;
        count = new int[num];
        count[loc]++;

        while (count[loc] != max) {
            if (count[loc] % 2 == 1) {
                loc = (loc + len) % num;

            } else {
                loc = loc - len < 0 ? loc - len + num : loc - len;
            }

            count[loc]++;
            res++;
        }
    }
}
