import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        ArrayList<Integer> list = new ArrayList<>();
        
        int[] day = new int[progresses.length];
        
        for(int i = 0; i < day.length; i++) {
            day[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        }
        
        int now = day[0];
        int cnt = 0;
        for(int i = 0; i < day.length; i++) {
            if(now < day[i]) {
                list.add(cnt);
                cnt = 1;
                now = day[i];
            } else {
                cnt++;
            }
        }
        
        if(cnt > 0) {
            list.add(cnt);
        }
        
        answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}