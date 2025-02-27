import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int row, col;
    static char[][] map;
    static boolean[][] pipe;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];

        for(int r = 0; r < row; r++) {
            map[r] = br.readLine().trim().toCharArray();
        }
    }

    static void getRes() {
        pipe = new boolean[row][col];

        for(int r = 0; r < row; r++) {
            if(map[r][0] == '.') {
                flag = false;
                DFS(r, 0);
            }

            // System.out.println(r);
            // for(char[] tmp : map) {
            //     System.out.println(Arrays.toString(tmp));
            // }
            // System.out.println();
        }
    }

    static void DFS(int locR, int locC) {
        // 가장 위를 지나는 파이프 연결 성공 시 더 이상 탐색 X
        if(flag) {
            return;
        }
        // 마지막 열 도착 시 파이프 매설
        if(locC == col - 1) {
            pipe[locR][locC] = true;

            res++;

            // int nowR = locR;

            // for(int c = col - 2; c >= 0; c--) {
            //     if(nowR > 0 && pipe[nowR - 1][c]) {
            //         nowR--;
            //     } else if(nowR < row - 1 && pipe[nowR + 1][c]) {
            //         nowR++;
            //     }

            //     map[nowR][c] = map[locR][locC];
            // }

            flag = true;

            // System.out.println("cycle: " + res);
            // for(boolean[] tmp : pipe) {
            //     for(int i = 0; i < tmp.length; i++) {
            //         System.out.printf("%d ", tmp[i] ? res : 0);
            //     }
            //     System.out.println();
            // }
            // System.out.println();

            return;
        }

        pipe[locR][locC] = true;

        if(locR > 0 && map[locR - 1][locC + 1] == '.' && !pipe[locR - 1][locC + 1]) {
            DFS(locR - 1, locC + 1);
        }

        if(map[locR][locC + 1] == '.' && !pipe[locR][locC + 1]) {
            DFS(locR, locC + 1);
        }
        
        if(locR < row - 1 && map[locR + 1][locC + 1] == '.' && !pipe[locR + 1][locC + 1]) {
            DFS(locR + 1, locC + 1);
        }
        
        // if(!flag) {
        //     pipe[locR][locC] = false;
        // }
    }
}
