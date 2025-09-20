import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int cnt;
    static int need;
    static int[] num;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        cnt = Integer.parseInt(br.readLine().trim());
        need = Integer.parseInt(br.readLine().trim());

        num = new int[cnt];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < cnt; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int left = 0;
        int right = cnt - 1;

        Arrays.sort(num);

        while (left < right) {
            int sum = num[left] + num[right];
            if (sum == need) {
                res++;
                left++;
                right--;
            } else if (sum < need) {
                left++;
            } else {
                right--;
            }
        }
    }
}
