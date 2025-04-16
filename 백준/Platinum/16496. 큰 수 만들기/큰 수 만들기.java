import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static String res;
    static int size;
    static String[] nums;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        size = Integer.parseInt(br.readLine().trim());

        nums = new String[size];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < size; i++) {
            nums[i] = st.nextToken();
        }
    }

    static void getRes() {
        Arrays.sort(nums, (o1, o2) -> {
            String num1 = o1 + o2;
            String num2 = o2 + o1;
            
            return num2.compareTo(num1);
        });
        
        if (nums[0].equals("0")) {
            res = "0";

            return;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }

        res = sb.toString();
    }
}
