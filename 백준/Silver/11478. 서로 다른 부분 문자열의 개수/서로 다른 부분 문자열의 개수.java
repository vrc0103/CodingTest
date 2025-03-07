import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;

    static class Trie {
        Trie[] apb = new Trie[26];

        public void add(char[] chs) {
            Trie node = this;

            for(char ch : chs) {
                int idx = ch - 'a';

                if(node.apb[idx] == null) {
                    node.apb[idx] = new Trie();
                    res++;
                }

                node = node.apb[idx];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.print(res);
    }

    static void getRes() throws Exception {
        char[] chs = br.readLine().trim().toCharArray();
        Trie root = new Trie();

        for(int i = 0; i < chs.length; i++) {
            root.add(Arrays.copyOfRange(chs, i, chs.length));
        }
    }
}
