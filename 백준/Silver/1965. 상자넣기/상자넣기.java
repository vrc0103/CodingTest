import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num;
    static int[] size;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        size = new int[num];
        
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < num; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        List<Integer> lis = new ArrayList<>();

        lis.add(size[0]);

        for(int i = 1; i < num; i++) {
            if(lis.get(lis.size() - 1) < size[i]) {
                lis.add(size[i]);
            } else {
                // 해당 값이 없으면 들어가야 할 위치 * (-1) - 1인 값을 반환함
                int idx = Collections.binarySearch(lis, size[i]);

                if(idx < 0) {
                    idx = -1 * idx - 1;

                    lis.set(idx, size[i]);
                }
            }
        }

        res = lis.size();
    }
}
