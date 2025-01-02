import java.io.*;
import java.util.*;

public class SWEA1936 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char res;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int A, B;

        st = new StringTokenizer(br.readLine().trim());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        if (A == 1 && B == 2 || A == 2 && B == 3 || A == 3 && B == 1) {
            res = 'B';
        } else {
            res = 'A';
        }
    }
}
