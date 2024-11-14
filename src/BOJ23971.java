import java.io.*;
import java.util.*;

public class BOJ23971 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int row, col;
    static int rowGap, colGap;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int rowPos, colPos;

        st = new StringTokenizer(br.readLine().trim());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        rowGap = Integer.parseInt(st.nextToken()) + 1;
        colGap = Integer.parseInt(st.nextToken()) + 1;

        // gap으로 나눈 나머지가 0 이상이면 한명 더 가능
        rowPos = row / rowGap;
        rowPos += row % rowGap > 0 ? 1 : 0;

        colPos = col / colGap;
        colPos += col % colGap > 0 ? 1 : 0;

        res = rowPos * colPos;
    }
}