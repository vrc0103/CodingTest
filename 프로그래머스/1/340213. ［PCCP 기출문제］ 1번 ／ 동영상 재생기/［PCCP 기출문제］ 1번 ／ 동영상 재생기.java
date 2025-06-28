import java.io.*;
import java.util.*;

class Solution {
    static int vid, now, start, end;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        StringTokenizer st = new StringTokenizer(video_len, ":");
        int vid_min = Integer.parseInt(st.nextToken());
        int vid_sec = Integer.parseInt(st.nextToken());
        vid = vid_min * 60 + vid_sec;
        
        st = new StringTokenizer(pos, ":");
        int pos_min = Integer.parseInt(st.nextToken());
        int pos_sec = Integer.parseInt(st.nextToken());
        now = pos_min * 60 + pos_sec;
        
        st = new StringTokenizer(op_start, ":");
        int start_min = Integer.parseInt(st.nextToken());
        int start_sec = Integer.parseInt(st.nextToken());
        start = start_min * 60 + start_sec;
        
        st = new StringTokenizer(op_end, ":");
        int end_min = Integer.parseInt(st.nextToken());
        int end_sec = Integer.parseInt(st.nextToken());
        end = end_min * 60 + end_sec;
        
        if(isOpening(now)) {
            now = end;
        }
        
        for(int i = 0; i < commands.length; i++) {
            if(commands[i].equals("next")) {
                now += 10;
            } else if(commands[i].equals("prev")) {
                now -= 10;
            }
            
            if(now > vid) {
                now = vid;
            } else if(now < 0) {
                now = 0;
            }
            
            if(isOpening(now)) {
                now = end;
            } 
        }
        
        StringBuilder sb = new StringBuilder();
        String min = Integer.toString(now / 60);
        String sec = Integer.toString(now % 60);
        
        if(min.length() == 1) {
            min = "0" + min;
        }
        
        if(sec.length() == 1) {
            sec = "0" + sec;
        }
        
        return min + ":" + sec;
    }
    
    static boolean isOpening(int now) {
        boolean res = false;
        
        if(start <= now && now <= end) {
            res = true;
        }
        
        return res;
    }
}