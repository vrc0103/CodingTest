import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int len;
    static int[] list;
    static ArrayList<Integer> LIS;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        len = Integer.parseInt(br.readLine().trim());
        list = new int[len];
        LIS = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        for(int i = 0; i < len; i++) {
            int size = LIS.size();
            if(size == 0 || LIS.get(size - 1) < list[i]) {
                LIS.add(list[i]);
            }

            else {
                int left = 0;
                int right = size - 1;

                while(left <= right) {
                    int mid = (left + right) / 2;

                    if(LIS.get(mid) >= list[i]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                LIS.set(left, list[i]);
            }
        }

        res = LIS.size();
    }
}
