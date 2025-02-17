import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int len, target;
    static int[] arr;
    static int[] count;

    public static void main(String[] args) throws Exception {
        // 정보
        st = new StringTokenizer(br.readLine().trim());
        len = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        arr = new int[len];
        count = new int[len];

        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 풀이
        int cnt = 0;
        int start = -1;
        int end = 0;

        while(cnt < target && end < len) {
            if(arr[end] == 1) {
                cnt++;

                if(start == -1) {
                    start = end;
                }

                count[end] = len;
            }

            if(cnt == target) {
                break;
            }

            end++;
        }

        if(end == len) {
            System.out.println(-1);

            return;
        }

        count[end] = end - start + 1;
        res = count[end];
        end++;

        while(end < len) {
            if(arr[end] == 1) {
                cnt++;
            }

            while(cnt > target) {
                start++;

                if(arr[start] == 1) {
                    cnt--;
                }
            }

            count[end] = end - start + 1;

            res = Math.min(res, count[end]);

            end++;
        }

        System.out.println(res);
    }
}