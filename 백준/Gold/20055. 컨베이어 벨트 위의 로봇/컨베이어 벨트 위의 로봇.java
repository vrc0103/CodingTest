import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int num;
    static int limit;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        belt = new int[2 * num];
        robot = new boolean[num];

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < 2 * num; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int count = 0;
        int rot;

        while (count < limit) {
            // 1. 벨트 회전
            rot = belt[2 * num - 1];
            for (int i = 2 * num - 1; i > 0; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = rot;

            // 벨트 회전에 따른 로봇 이동
            for (int i = num - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[num - 1] = false; // 내리는 위치
            robot[0] = false;

            // 2. 로봇 이동
            for (int i = num - 2; i > 0; i--) {
                if (robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
                    robot[i + 1] = true;
                    robot[i] = false;

                    // 내구도 감소
                    belt[i + 1]--;
                }
            }

            // 3. 로봇 추가
            if (belt[0] > 0) {
                robot[0] = true;
                belt[0]--;
            }

            // 4. 내구도 확인
            count = 0;

            for (int i = 0; i < 2 * num; i++) {
                if (belt[i] == 0) {
                    count++;
                }
            }

            res++;
        }
    }
}
