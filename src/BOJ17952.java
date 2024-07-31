import java.io.*;
import java.util.*;

public class BOJ17952 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;

    static class Homework {
        int point;
        int minute;

        public Homework(int point, int minute) {
            this.point = point;
            this.minute = minute;
        }
    }

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int time = Integer.parseInt(br.readLine());
        int oper, point, minute;
        Stack<Homework> stack = new Stack<>();
        Homework now;

        res = 0;

        for (int t = 0; t < time; t++) {
            st = new StringTokenizer(br.readLine());
            oper = Integer.parseInt(st.nextToken());

            if (oper == 1) { // 새 과제를 스택에 추가
                point = Integer.parseInt(st.nextToken());
                minute = Integer.parseInt(st.nextToken());
                stack.push(new Homework(point, minute));
            }

            if (stack.isEmpty()) {
                continue;
            }

            now = stack.pop();
            if (now.minute == 1) {
                res += now.point;
            } else {
                stack.push(new Homework(now.point, now.minute - 1));
            }
        }
    }
}
