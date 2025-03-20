import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size;
    static int row, col;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes(row, col, 0);

        System.out.print(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
    }

    static void getRes(int r, int c, int num) {
        // System.out.printf("%d %d %d / %d\n", size, r, c, num);
        if(size == 0) {
            res = num;

            return;
        }

        int mid = (int) Math.pow(2, size - 1);
        int idx = 0;

        if(r < mid && c < mid) {
            idx = 0;
        }
        
        else if(r < mid && c >= mid) {
            idx = 1;
        }

        else if(r >= mid && c < mid) {
            idx = 2;
        }

        else {
            idx = 3;
        }

        num += mid * mid * idx;
        size--;

        r = r - mid < 0 ? r : r - mid;
        c = c - mid < 0 ? c : c - mid;

        getRes(r, c, num);
    }
}
