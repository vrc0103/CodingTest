import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int cmd;
    static ArrayList<Integer>[] gear;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        res = 0;
        int cmd = Integer.parseInt(br.readLine().trim());

        gear = new ArrayList[4];
        for(int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine().trim());
            gear[i] = new ArrayList<Integer>();

            for(int j = 0; j < 8; j++) {
                gear[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        while(cmd-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int target = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            boolean[] rotate = new boolean[4];
            rotate[target] = true;

            // 왼쪽 톱니
            for(int i = target; i >= 1; i--) {
                if(gear[i].get(6) != gear[i - 1].get(2)) {
                    rotate[i - 1] = true;
                } else {
                    break;
                }
            }

            // 오른쪽 톱니
            for(int i = target; i < 3; i++) {
                if(gear[i].get(2) != gear[i + 1].get(6)) {
                    rotate[i + 1] = true;
                } else {
                    break;
                }
            }

            // 회전
            for(int i = 0; i < 4; i++) {
                if(rotate[i]) {
                    if(target % 2 == i % 2) {
                        if(dir == 1) {
                            gear[i].add(0, gear[i].remove(7));
                        } else {
                            gear[i].add(gear[i].remove(0));
                        }
                    } else {
                        if(dir == -1) {
                            gear[i].add(0, gear[i].remove(7));
                        } else {
                            gear[i].add(gear[i].remove(0));
                        }
                    }
                }
            }
        }

        for(int i = 0; i < 4; i++) {
            res += gear[i].get(0) * (int) Math.pow(2, i);
        }
    }
}
