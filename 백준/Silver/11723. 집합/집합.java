import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static boolean[] arr;
    static String[] cmds = {"add", "remove", "check", "toggle", "all", "empty"};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        int cnt = Integer.parseInt(br.readLine().trim());

        arr = new boolean[21];

        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());

            String cmd = st.nextToken();
            int operand = 0;

            for(int i = 0; i < cmds.length; i++) {
                if(cmd.equals(cmds[i])) {
                    if(i < 4) {
                        operand = Integer.parseInt(st.nextToken());
                    }

                    execute(i, operand);

                    break;
                }
            }
        }
    }

    static void execute(int idx, int operand) {
        if(idx == 0) {
            arr[operand] = true;
        } else if(idx == 1) {
            arr[operand] = false;
        } else if(idx == 2) {
            sb.append(arr[operand] ? 1 : 0).append("\n");
        } else if(idx == 3) {
            arr[operand] = arr[operand] ? false : true;
        } else if(idx == 4) {
            Arrays.fill(arr, true);
        } else if(idx == 5) {
            Arrays.fill(arr, false);
        }
    }
}

