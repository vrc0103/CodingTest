import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res = 1;
    static int start, end;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.println(res);
    }

    static void getRes() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        while(end > start) {
            if(end % 10 == 1) {
                end /= 10;
                res++;
            } else if(end % 2 == 0) {
                end /= 2;
                res++;
            }
            // 홀수인데 끝자리가 1 아니면 불가능한 숫자임
            else {
                res = -1;
                return;
            }
        }

        if(end != start) {
            res = -1;
        }
    }
}

