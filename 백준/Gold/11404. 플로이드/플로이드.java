import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numN, numE;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 정보
        numN = Integer.parseInt(br.readLine().trim());
        numE = Integer.parseInt(br.readLine().trim());

        map = new int[numN + 1][numN + 1];
        for(int i = 1; i <= numN; i++) {
            Arrays.fill(map[i], 100_000_000);
            map[i][i] = 0;
        }

        for(int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[from][to] = Math.min(map[from][to], cost);
        }

        // for(int[] tmp : map) System.out.println(Arrays.toString(tmp));

        // 풀이
        // 플로이드 - 워셜
        for (int k = 1; k <= numN; k++) {
            for (int i = 1; i <= numN; i++) {
                for (int j = 1; j <= numN; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for(int r = 1; r <= numN; r++) {
            for(int c = 1; c <= numN; c++) {
                sb.append(map[r][c] == 100_000_000 ? 0 : map[r][c]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}