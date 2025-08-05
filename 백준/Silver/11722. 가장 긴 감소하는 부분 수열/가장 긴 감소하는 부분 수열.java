import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int[] num;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        int cnt = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        num = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        ArrayList<Integer> LDS = new ArrayList<>();
        LDS.add(num[num.length - 1]);

        for (int i = num.length - 2; i >= 0; i--) {
            if (LDS.get(LDS.size() - 1) < num[i]) {
                LDS.add(num[i]);
            } else {
                int idx = Collections.binarySearch(LDS, num[i]);

                if (idx < 0) {
                    idx = (idx * -1) - 1;
                    LDS.set(idx, num[i]);
                }
            }
        }

        res = LDS.size();
    }
}
