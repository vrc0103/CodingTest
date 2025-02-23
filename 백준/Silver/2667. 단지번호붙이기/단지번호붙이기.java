import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    static int size;
    static int[][] map;
    static boolean[][] checked;
    static ArrayList<Integer> cnt;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 정보
        size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        checked = new boolean[size][size];

        for(int r = 0; r < size; r++) {
            String input = br.readLine().trim();

            for(int c = 0; c < size; c++) {
                map[r][c] = input.charAt(c) - '0';
            }
        }

        // 풀이
        cnt = new ArrayList<>();

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if(map[r][c] == 1 && !checked[r][c]) {
                    int count = 0;

                    queue.add(new int[] {r, c});
                    checked[r][c] = true;
                    count++;

                    while(!queue.isEmpty()) {
                        int[] now = queue.remove();
                        
                        for(int i = 0; i < 4; i++) {
                            int nextR = now[0] + dR[i];
                            int nextC = now[1] + dC[i];

                            if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size || map[nextR][nextC] == 0 || checked[nextR][nextC]) {
                                continue;
                            }

                            queue.add(new int[] {nextR, nextC});
                            checked[nextR][nextC] = true;
                            count++;
                        }
                    }

                    cnt.add(count);
                }
            }
        }

        Collections.sort(cnt);

        sb.append(cnt.size()).append("\n");
        for(int i : cnt) {
            sb.append(i).append("\n");
        }

        System.out.print(sb);
    }
}
