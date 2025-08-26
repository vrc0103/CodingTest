import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int num;
    static String[] word;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());

        word = new String[num];
        for (int i = 0; i < num; i++) {
            word[i] = br.readLine().trim();
        }
    }

    static void getRes() {
        int res = -1;
        String res1 = null;
        String res2 = null;

        for (int i = 0; i < num; i++) {
            String word1 = word[i];

            for (int j = i + 1; j < num; j++) {
                String word2 = word[j];
                int cnt;

                for (cnt = 0; cnt < word1.length() && cnt < word2.length(); cnt++) {
                    if (word1.charAt(cnt) != word2.charAt(cnt)) {
                        break;
                    }
                }

                if (cnt > res) {
                    res1 = word1;
                    res2 = word2;
                    res = cnt;
                }
            }
        }

        sb.append(res1).append("\n").append(res2).append("\n");
    }
}
