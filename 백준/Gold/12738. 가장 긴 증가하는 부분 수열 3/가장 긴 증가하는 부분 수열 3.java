import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        arr = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        List<Integer> LCS = new ArrayList<>();

        LCS.add(arr[0]);

        for (int i = 1; i < len; i++) {
            int idx = Collections.binarySearch(LCS, arr[i]);

            if (idx >= 0) {
                continue;
            }

            idx = (idx + 1) * -1;

            if (idx == LCS.size()) {
                LCS.add(arr[i]);
            } else {
                LCS.set(idx, arr[i]);
            }
        }

        res = LCS.size();
    }
}
