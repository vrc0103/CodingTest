import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int len;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        len = Integer.parseInt(br.readLine().trim());

        arr = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }
    }

    static void getRes() {
        boolean[] selected = new boolean[len + 1];
        Set<Integer> set = new TreeSet<>();

        for (int i = 1; i <= len; i++) {
            if (!selected[i]) {
                int now = i;

                while (!selected[now]) {
                    selected[now] = true;
                    now = arr[now];
                }

                if (now == i) {
                    for (int j = 1; j <= len; j++) {
                        if (selected[j]) {
                            set.add(j);
                        }
                    }
                }

                selected = new boolean[len + 1];
            }
        }

        sb.append(set.size()).append("\n");
        for (int i : set) {
            sb.append(i).append("\n");
        }
    }
}
