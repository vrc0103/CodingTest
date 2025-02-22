import java.io.*;
import java.util.*;

public class Main {
    static int res;
    static int[][][] originMap;
    static int[][] originFish;

    static int[] dR = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dC = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./.vscode/testCase.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정보
        originMap = new int[2][4][4];
        originFish = new int[17][2];

        for(int r = 0; r < 4; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int c = 0; c < 4; c++) {
                originMap[0][r][c] = Integer.parseInt(st.nextToken());
                originMap[1][r][c] = Integer.parseInt(st.nextToken());

                originFish[originMap[0][r][c]][0] = r;
                originFish[originMap[0][r][c]][1] = c;
            }
        }

        // 풀이
        /*
         * 1. 상어는 (0, 0)에 있는 물고기를 먹고 시작, 상어의 이동 방향은 잡아먹은 물고기의 이동 방향과 동일
         * 2. 살아있는 물고기는 작은 번호의 물고기부터 저장된 방향으로 이동
         * 3. 이동이 불가능한 방향이면 45도씩 반시계방향으로 회전하며 이동 가능한 방향 탐색
         * 4. 물고기의 이동 위치에 다른 물고기가 있으면 해당 물고기와 자리를 바꿈
         * 5. 모든 물고기의 이동이 완료되면 상어가 저장된 방향으로 이동하며 물고기를 잡아먹음
         * 6. 상어는 여러 칸 이동할 수 있으며, 도착한 위치의 물고기만 잡아먹음
         * 7-1. 상어가 물고기를 잡아먹으면 2번부터 반복
         * 7-2. 잡아먹을 수 없으면 시뮬레이션 종료
         */
        // map : 양수 = 물고기 번호, 0 = 빈칸, -1 = 상어 위치
        int numF;

        // 1번
        numF = originMap[0][0][0];
        originFish[numF][0] = -1;
        originFish[numF][1] = -1;
        originMap[0][0][0] = -1;

        DFS(2, originMap, originFish, false);

        System.out.println(res);
    }

    // cnt는 먹힌 사이클 횟수를 확인하기 위한 변수 -> 풀이에는 의미 없음
    static void DFS(int cnt, int[][][] recM, int[][] recF, boolean end) {
        if(end) {
            // 결과 갱신
            int sum = 0;

            for(int i = 1; i < 17; i++){
                if(recF[i][0] < 0) {
                    sum += i;
                }
            }

            res = Math.max(res, sum);

            // System.out.println(cnt + "map");
            // for(int[] a : recM[0]) {
            //     for(int i = 0; i < 4; i++) {
            //         System.out.printf("%3d ", a[i]);
            //     }
            //     System.out.println();
            // }
            // System.out.println(cnt + "dir");
            // for(int[] a : recM[1]) {
            //     for(int i = 0; i < 4; i++) {
            //         System.out.printf("%3d ", a[i]);
            //     }
            //     System.out.println();
            // }
            // for(int i = 0; i < 17; i++){
            //     System.out.printf("%3d ", recF[i][0]);
            // }
            // System.out.println();
            // for(int i = 0; i < 17; i++){
            //     System.out.printf("%3d ", recF[i][1]);
            // }
            // System.out.println();
            // System.out.println(sum);
            // System.out.println();

            return;
        }

        int dir;
        int[] now;
        int nextR, nextC;
        int[][][] tmpM = new int[2][4][4];
        int[][] tmpF = new int[17][2];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                tmpM[0][i][j] = recM[0][i][j];
                tmpM[1][i][j] = recM[1][i][j];
            }
        }

        for(int i = 0; i < 17; i++) {
            tmpF[i][0] = recF[i][0];
            tmpF[i][1] = recF[i][1];
        }

        // 2번
        for(int i = 1; i < 17; i++) {
            // 해당 물고기가 살아있으면
            if(tmpF[i][0] >= 0) {
                now = tmpF[i];
                dir = tmpM[1][now[0]][now[1]];
                
                // 3번
                while(true) {
                    nextR = now[0] + dR[dir];
                    nextC = now[1] + dC[dir];

                    if(nextR < 0 || nextR >= 4 || nextC < 0 || nextC >= 4 || tmpM[0][nextR][nextC] == -1) {
                        dir++;

                        if(dir == 9) {
                            dir = 1;
                        }
                    } else {
                        break;
                    }
                }
                
                // 4번
                if(tmpM[0][nextR][nextC] > 0) {
                    // 물고기 있으면 스왑
                    tmpF[tmpM[0][nextR][nextC]][0] = now[0];
                    tmpF[tmpM[0][nextR][nextC]][1] = now[1];
                    tmpM[0][now[0]][now[1]] = tmpM[0][nextR][nextC];
                    tmpM[1][now[0]][now[1]] = tmpM[1][nextR][nextC];
                } else {
                    // 빈 칸이면 그냥 이동
                    tmpM[0][now[0]][now[1]] = 0;
                    tmpM[1][now[0]][now[1]] = 0;
                }
                // 위치 갱신
                tmpF[i][0] = nextR;
                tmpF[i][1] = nextC;
                tmpM[0][nextR][nextC] = i;
                tmpM[1][nextR][nextC] = dir;
            }
        }

        // 5번
        int len = 0;
        int numF;

        while(true) {
            len++;
            dir = tmpM[1][tmpF[0][0]][tmpF[0][1]];

            nextR = tmpF[0][0] + len * dR[dir];
            nextC = tmpF[0][1] + len * dC[dir];

            // 7-2번
            if(nextR < 0 || nextR >= 4 || nextC < 0 || nextC >= 4) {
                DFS(cnt, tmpM, tmpF, true);

                break;
            }

            // 6번
            if(tmpM[0][nextR][nextC] == 0) {
                continue;
            }

            numF = tmpM[0][nextR][nextC];    // 먹힌 물고기
            tmpF[numF][0] = -1 * cnt;
            tmpF[numF][1] = -1 * cnt;

            tmpM[0][tmpF[0][0]][tmpF[0][1]] = 0; // 원래 상어 자리는 빈칸으로
            tmpM[0][nextR][nextC] = -1;      // 상어 위치 갱신
            tmpF[0][0] = nextR;
            tmpF[0][1] = nextC;

            // 7-1번 
            DFS(cnt + 1, tmpM, tmpF, false);

            // 원복
            tmpF[numF][0] = nextR;
            tmpF[numF][1] = nextC;
            tmpM[0][nextR][nextC] = numF;

            tmpF[0][0] = nextR - len * dR[dir];
            tmpF[0][1] = nextC - len * dC[dir];
            tmpM[0][tmpF[0][0]][tmpF[0][1]] = -1;
        }
    }
}
