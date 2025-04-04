import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int size;

    // 각 열과 두 대각선을 체크할 배열
    static boolean[] col;
    static boolean[] slash;      // (row + col) 값을 사용 (우측 대각선)
    static boolean[] backSlash;  // (row - col + size - 1) 값을 사용 (좌측 대각선)

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        solveNQueen();
        
        System.out.print(res);
    }
    
    static void solveNQueen() throws Exception {
        res = 0;
        size = Integer.parseInt(br.readLine().trim());

        // 배열 크기 설정: col는 size, slash와 backSlash는 2*size - 1
        col = new boolean[size];
        slash = new boolean[2 * size - 1];
        backSlash = new boolean[2 * size - 1];
        
        dfs(0);
    }
    
    static void dfs(int r) {
        // 모든 행 탐색 완료 시 재귀 탈출
        if (r == size) {
            res++;
            return;
        }
        
        for (int c = 0; c < size; c++) {
            // 현재 (r, c)에 퀸을 놓을 수 있는지 체크
            if (!col[c] && !slash[r + c] && !backSlash[r - c + size - 1]) {
                // 해당 열과 대각선에 퀸 배치 표시
                col[c] = true;
                slash[r + c] = true;
                backSlash[r - c + size - 1] = true;
                
                dfs(r + 1);
                
                // 원복
                col[c] = false;
                slash[r + c] = false;
                backSlash[r - c + size - 1] = false;
            }
        }
    }
}
