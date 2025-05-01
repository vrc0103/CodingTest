import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int len, target;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        len = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken()) - 1;

        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        res = list.get(target);
    }
}

