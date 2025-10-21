import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int num;
    static int[] val;
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.println(sb);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());

        val = new int[num];
        st = new StringTokenizer(br.readLine().trim());

        for(int i = 0; i < num; i++) {
            val[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int[] res = new int[3];
        long absMin = 3_000_000_000L;

        Arrays.sort(val);

        loop: for(int i = 0; i < num - 2; i++) {
            int left = i + 1;
            int right = num - 1;

            while(left < right) {
                long sum = (long) val[left] + val[i] + val[right];

                if(Math.abs(sum) < absMin) {
                    res[0] = val[left];
                    res[1] = val[i];
                    res[2] = val[right];

                    absMin = Math.abs(sum);
                }

                if(sum == 0) {
                    break loop;
                }
            

                if(sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        
        Arrays.sort(res);

        for(int i = 0; i < 3; i++) {
            sb.append(res[i]).append(" ");
        }
    }
}
