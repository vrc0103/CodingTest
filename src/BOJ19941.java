import java.io.*;
import java.util.*;

public class BOJ19941 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int length, range;
    static char[] table;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        String input;

        st = new StringTokenizer(br.readLine().trim());
        length = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());

        table = new char[length];
        selected = new boolean[length];

        input = br.readLine().trim();
        for (int i = 0; i < length; i++) {
            table[i] = input.charAt(i);

            if (table[i] == 'P') {
                selected[i] = true;
            } else {
                selected[i] = false;
            }
        }
    }

    static void getRes() {
        for (int i = 0; i < length; i++) {
            if (table[i] == 'P') {
                for (int j = i - range; j <= i + range; j++) {
                    if (j < 0 || j >= length) {
                        continue;
                    }

                    // 범위 내 선택되지 않은 햄버거 존재
                    if (!selected[j]) {
                        selected[j] = true;
                        res++;
                        break;
                    }
                }
            }
        }
    }
}
