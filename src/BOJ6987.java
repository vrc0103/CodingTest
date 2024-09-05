import java.io.*;
import java.util.*;

public class BOJ6987 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int res;
    static int[][] score;
    static boolean judgeInput;

    public static void main(String[] args) throws IOException {
        for (int tc = 0; tc < 4; tc++) {
            getScore();
        }

        System.out.println(sb.toString());
    }

    static void getScore() throws IOException {
        score = new int[6][3]; // 팀 별 승, 무, 패

        judgeInput = true;
        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < 6; i++) {
            score[i][0] = Integer.parseInt(st.nextToken());
            score[i][1] = Integer.parseInt(st.nextToken());
            score[i][2] = Integer.parseInt(st.nextToken());

            if (score[i][0] + score[i][1] + score[i][2] != 5) {
                judgeInput = false;
                break;
            }
        }

        res = 0;

        if (judgeInput) {
            judge(0, 1);
        }

        sb.append(res).append(" ");
    }

    static void judge(int team1, int team2) {
        // 이전 재귀에서 가능한 경우로 판단되면 수행 X
        if (res == 1) {
            return;
        }

        // 모든 팀의 경우 파악 시 재귀 종료
        if (team1 == 5) {
            res = 1;
            return;
        }

        // team1의 승리 수가 남아있고, team2의 패배 수가 남은 경우
        if (score[team1][0] > 0 && score[team2][2] > 0) {
            score[team1][0]--;
            score[team2][2]--;

            // 해당 팀의 모든 경우 계산 -> 다음 팀의 승, 무, 패 계산
            if (team2 == 5) {
                judge(team1 + 1, team1 + 2);
            }
            // 모든 경우 파악 X -> 상대 팀을 바꿔가며 탐색 진행
            else {
                judge(team1, team2 + 1);
            }

            // 재귀 탈출 시 배열 원복
            score[team1][0]++;
            score[team2][2]++;
        }

        // team1, team2의 무승부 수가 남은 경우
        if (score[team1][1] > 0 && score[team2][1] > 0) {
            score[team1][1]--;
            score[team2][1]--;

            if (team2 == 5) {
                judge(team1 + 1, team1 + 2);
            } else {
                judge(team1, team2 + 1);
            }

            score[team1][1]++;
            score[team2][1]++;
        }

        // team1의 패배 수가 남아있고, team2의 승리 수가 남은 경우
        if (score[team1][2] > 0 && score[team2][0] > 0) {
            score[team1][2]--;
            score[team2][0]--;

            if (team2 == 5) {
                judge(team1 + 1, team1 + 2);
            } else {
                judge(team1, team2 + 1);
            }

            score[team1][2]++;
            score[team2][0]++;
        }
    }
}
