import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int width;
    static Trie trie;

    static class Trie {
        Map<String, Trie> node;

        public Trie() {
            this.node = new TreeMap<String, Trie>();
        }

        public void add(String input) {
            st = new StringTokenizer(input);
            int depth = Integer.parseInt(st.nextToken());
            Trie trie = this;

            for(int i = 0; i < depth; i++) {
                String str = st.nextToken();

                if(trie.node.get(str) == null) {
                    trie.node.put(str, new Trie());
                }

                trie = trie.node.get(str);
            }
        }

        public void print(int depth) {
            Trie trie = this;

            for(String str : trie.node.keySet()) {
                for(int i = 0; i < depth; i++) {
                    sb.append("--");
                }
                sb.append(str).append("\n");

                trie.node.get(str).print(depth + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.println(sb);
    }

    static void getRes() throws Exception {
        width = Integer.parseInt(br.readLine().trim());

        trie = new Trie();

        for(int i = 0; i < width; i++) {
            trie.add(br.readLine().trim());
        }

        trie.print(0);
    }
}