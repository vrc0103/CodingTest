import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static char res;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        String word = br.readLine().trim();
        int[] cnt = new int[26];
        int max = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = -1;

            if ('a' <= ch && ch <= 'z') {
                idx = ch - 'a';
            } else if ('A' <= ch && ch <= 'Z') {
                idx = ch - 'A';
            }

            cnt[idx]++;
        }

        for (int i = 0; i < 26; i++) {
            if (cnt[i] == max) {
                res = '?';
            } else if (cnt[i] > max) {
                res = (char) ('A' + i);
                max = cnt[i];
            }
        }
    }
}
