import java.util.*;

class Solution {
    public static class Trie {
        HashMap<Character, Trie> child;
        boolean isEnd;
        
        public Trie() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
        
        public boolean add(String str) {
            boolean res = true;
            Trie trie = this;
            int len = str.length();
            
            for(int i = 0; i < len; i++) {
                char ch = str.charAt(i);
                
                if(trie.child.get(ch) == null) {
                    trie.child.put(ch, new Trie());
                }
                
                trie = trie.child.get(ch);
                
                if(trie.isEnd) {
                    res = false;
                }
                
                if(i == len - 1) {
                    trie.isEnd = true;
                }
            }
            
            if(trie.child.size() > 0) {
                res = false;
            }
            
            return res;
        }
    }
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Trie trie = new Trie();
        
        for(int i = 0; i < phone_book.length; i++) {
            if(answer) {
                answer = trie.add(phone_book[i]);
            } else {
                break;
            }
        }
        
        return answer;
    }
}