import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int len = citations.length;
        
        Arrays.sort(citations);
        
        for(int i = 0; i < len; i++) {
            int cnt = len - i;
            
            if(cnt <= citations[i]) {
                answer = cnt;
                
                break;
            }
            
            // System.out.println(citations[i] + " " + answer);
        }
        
        return answer;
    }
}