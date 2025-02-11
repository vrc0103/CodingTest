import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int num;
    static int res;
    static int[][] synergy;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        getTeam();
    }

    static void getTeam() throws IOException {
        num = Integer.parseInt(br.readLine());
        synergy = new int[num][num];
        selected = new boolean[num];
        res = Integer.MAX_VALUE;

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < num; j++) {
                synergy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getComb(0, 0);

        System.out.println(res);
    }

    static void getComb(int sel, int cnt) {
        // 팀 결성
        if (cnt == num / 2) {
            int team1 = 0;
            int team2 = 0;

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    // 1팀 능력치
                    if (selected[i] && selected[j]) {
                        team1 += synergy[i][j];
                    }

                    // 2팀 능력치
                    if (!selected[i] && !selected[j]) {
                        team2 += synergy[i][j];
                    }
                }
            }

            res = Math.min(res, Math.abs(team1 - team2));

            return;
        }

        for (int i = sel; i < num; i++) {
            if (!selected[i]) {
                selected[i] = true;
                getComb(i + 1, cnt + 1);
                selected[i] = false;
            }
        }
    }
}
