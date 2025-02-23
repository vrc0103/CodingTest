import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int row, col;
    static int[][] ice, melt, count;

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 정보
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        ice = new int[row][col];

        for(int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < col; c++) {
                ice[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 풀이
        int cnt;
        int nextR, nextC;
        int[] now;
        Queue<int[]> same = new ArrayDeque<>();

        while(true) {
            res++;
            cnt = 0;

            // 얼음 녹이기
            melt = new int[row][col];

            for(int r = 1; r < row - 1; r++) {
                for(int c = 1; c < col - 1; c++) {
                    melt[r][c] = ice[r][c];

                    if(melt[r][c] > 0) {
                        for(int i = 0; i < 4; i++) {
                            if(ice[r + dR[i]][c + dC[i]] == 0) {
                                melt[r][c]--;
                            }
                        }

                        melt[r][c] = Math.max(melt[r][c], 0);
                    }
                }
            }

            // 얼음 갱신
            ice = melt;

            // 얼음 덩어리 카운트
            count = new int[row][col];

            for(int r = 1; r < row - 1; r++) {
                for(int c = 1; c < col - 1; c++) {
                    if(ice[r][c] > 0 && count[r][c] == 0) {
                        cnt++;
                        count[r][c] = cnt;
                        same.add(new int[] {r, c});

                        while(!same.isEmpty()) {
                            now = same.remove();

                            for(int i = 0; i < 4; i++) {
                                nextR = now[0] + dR[i];
                                nextC = now[1] + dC[i];

                                // 빙산 중 아직 선택 안된 빙산
                                if(ice[nextR][nextC] > 0 && count[nextR][nextC] == 0) {
                                    count[nextR][nextC] = cnt;
                                    same.add(new int[] {nextR, nextC});
                                }
                            }
                        }
                    }
                }
            }

            // for(int[] tmp : ice) {
            //     System.out.println(Arrays.toString(tmp));
            // }
            // System.out.println();

            if(cnt != 1) {
                break;
            }
        }

        if(cnt == 0) {
            res = 0;
        }

        System.out.println(res);
    }
}
