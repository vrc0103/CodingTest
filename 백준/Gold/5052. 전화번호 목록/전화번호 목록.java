import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static String res;

    static class Trie {
        boolean isEnd = false;              // 현재 노드가 마지막
        boolean hasChild = false;           // 현재 노드 이후에 추가 노드가 있는지
        Trie[] children = new Trie[10];     // 0 ~ 9로 다음 숫자가 있는지

        public boolean add(String nums) {
            Trie node = this;

            for(char c : nums.toCharArray()) {
                int num = c - '0';

                // 다음 숫자가 없던 숫자면 새로 추가
                if(node.children[num] == null) {
                    node.children[num] = new Trie();
                    node.hasChild = true;
                }

                // 다음 숫자로 이동
                node = node.children[num];

                // 노드가 마지막 = 저장된 전화번호가 현재 전화번호의 접두사임
                if (node.isEnd) {
                    return false;
                }
            }

            // 마지막 노드 표시
            node.isEnd = true;

            // 전화번호 끝났는데 현재 노드에 자식 노드 존재 = 현재 전화번호가 저장된 전화번호의 접두사임
            if(node.hasChild) {
                return false;
            }

            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            getRes();

            sb.append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        int cnt = Integer.parseInt(br.readLine().trim());
        Trie trie = new Trie();
        boolean pos = true;
        
        while(cnt-- > 0) {
            String input = br.readLine().trim();

            if(pos && !trie.add(input)) {
                pos = false;
            }
        }
        
        res = pos ? "YES" : "NO";
    }
}
