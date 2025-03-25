import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int num;
    static int sel;
    static int[] card;
    static int[] nums;
    static boolean[] selected;
    static HashSet<Integer> hset = new HashSet<>();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes(0);

        System.out.print(hset.size());
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        sel = Integer.parseInt(br.readLine().trim());

        card = new int[num];
        nums = new int[sel];
        selected = new boolean[num];

        for(int i = 0; i < num; i++) {
            card[i] = Integer.parseInt(br.readLine().trim());
        }
    }

    static void getRes(int cnt) {
        if(cnt == sel) {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < cnt; i++) {
                sb.append(nums[i]);
            }

            hset.add(Integer.parseInt(sb.toString()));

            return;
        }

        for(int i = 0; i < num; i++) {
            if(!selected[i]) {
                selected[i] = true;
                nums[cnt] = card[i];
                getRes(cnt + 1);
                selected[i] = false;
            }
        }
    }
}
