import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int len, sel;
    static int[] arr;
    static int[] selected;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        comb(0, 0);

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());

        len = Integer.parseInt(st.nextToken());
        sel = Integer.parseInt(st.nextToken());

        arr = new int[len];
        selected = new int[sel];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
    }

    static void comb(int idx, int cnt) {
        if(cnt == sel) {
            for(int i = 0; i < sel; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            
            return;
        }

        for(int i = idx; i < len; i++) {
            selected[cnt] = arr[i];
            comb(i, cnt + 1);
        }
    }
}

