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

            flag = true;

            return;
        }

        pipe[locR][locC] = true;

        for(int dR = -1; dR <= 1; dR++) {
            int nextR = locR + dR;
            int nextC = locC + 1;

            if(nextR < 0 || nextR >= row || map[nextR][nextC] == 'x' || pipe[nextR][nextC]) {
                continue;
            }

            DFS(nextR, nextC);
        }
        
        // 어차피 연결에 실패한 루트이므로 다시 봐도 다시 실패함 -> 굳이 원복하지 않아도 됨
        // if(!flag) {
        //     pipe[locR][locC] = false;
        // }
    }
}
