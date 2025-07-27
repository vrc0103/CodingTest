import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.println(sb);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());

        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        int need = Integer.parseInt(br.readLine().trim());

        hour += need / 60;
        min += need % 60;

        if (min >= 60) {
            min -= 60;
            hour += 1;
        }

        if (hour >= 24) {
            hour -= 24;
        }

        sb.append(hour).append(" ").append(min);
    }
}
