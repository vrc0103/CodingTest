import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int size, len;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        res = 0;

        st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());

        map = new int[size][size];
        for(int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getRes() {
        calc(true);
        calc(false);
    }

    static void calc(boolean flag) {
        loop: for(int i = 0; i < size; i++) {
            boolean needCheck = false;

            int nowLen = 0;
            int nowHeight = flag ? map[i][0] : map[0][i];

            for(int j = 0; j < size; j++) {
                int r = flag ? i : j;
                int c = flag ? j : i;

                // 내리막 경사 설치
                if(needCheck && nowLen == len) {
                    nowLen -= len;
                    needCheck = false;
                }
                
                // 동일한 높이이면 길이 증가
                if(map[r][c] == nowHeight) {
                    nowLen++;

                    // 끝까지 왔는데 경사로 길이 부족
                    if(needCheck && j == size - 1 && nowLen < len) {
                        continue loop;
                    }
                }
                // 두 칸 이상 차이나면 불가능
                else if(Math.abs(nowHeight - map[r][c]) > 1) {
                    continue loop;
                }
                // 이전 칸보다 한 칸 높아짐
                else if(nowHeight == map[r][c] - 1) {
                    if(nowLen < len) {
                        continue loop;
                    } else {
                        nowLen = 1;
                        nowHeight = map[r][c];
                    }
                }
                // 이전 칸보다 한 칸 낮아짐
                else if(nowHeight == map[r][c] + 1) {
                    // 경사로 완성 전에 또 낮아짐
                    if(needCheck) {
                        continue loop;
                    }

                    needCheck = true;
                    nowLen = 1;
                    nowHeight = map[r][c];

                    if(j == size - 1 && len > 1) {
                        continue loop;
                    }
                }
            }
            
            // System.out.println((flag ? "행부터" : "열부터") + ", " + i);
            res++;
        }
    }
}
