import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int size;
    static int count;
    static int[][] ice;
    static int[] power;

    static int[] dR = { 0, 1, 0, -1 };
    static int[] dC = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        getMap();

        getIce();

        System.out.print(sb.toString());
    }

    static void getMap() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        // Math.pow()는 Double로 반환되므로 형 변환이 필요함
        size = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        count = Integer.parseInt(st.nextToken());

        ice = new int[size][size];
        power = new int[count];

        for (int r = 0; r < size; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < size; c++) {
                ice[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < count; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getIce() {
        int[][] rotation;
        int subSize;
        int nextR, nextC;

        for (int cast = 0; cast < count; cast++) {
            subSize = (int) Math.pow(2, power[cast]);

            // 부분 격자 회전
            if (subSize > 1) {
                rotation = new int[size][size];

                // 회전시킬 부분 격자 선택
                for (int i = 0; i < size / subSize; i++) {
                    for (int j = 0; j < size / subSize; j++) {
                        // 회전된 원소 입력
                        for (int r = 0; r < subSize; r++) {
                            for (int c = 0; c < subSize; c++) {
                                rotation[subSize * i + r][subSize * j + c] = ice[subSize * (i + 1) - c - 1][subSize * j
                                        + r];
                            }
                        }
                    }
                }

                ice = rotation;
            }

            // 인접 3칸 이상 얼음이 연결되어 있지 않으면 1 감소
            int linked;
            int[] melt;
            Queue<int[]> melted = new LinkedList<>();

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (ice[r][c] == 0) {
                        continue;
                    }

                    linked = 0;

                    for (int i = 0; i < 4; i++) {
                        nextR = r + dR[i];
                        nextC = c + dC[i];

                        if (nextR >= 0 && nextR < size && nextC >= 0 && nextC < size && ice[nextR][nextC] > 0) {
                            linked++;
                        }
                    }

                    // 연쇄적으로 얼음이 녹는 것을 방지하기 위한 Queue
                    if (linked < 3) {
                        melted.add(new int[] { r, c });
                    }
                }
            }

            while (!melted.isEmpty()) {
                melt = melted.poll();
                ice[melt[0]][melt[1]]--;
            }
        }

        // 남은 얼음의 총합, 가장 큰 덩어리의 크기 계산
        int val = 0;
        int iceSize = 0;
        int maxIceSize = 0;
        int[] nowIce;
        boolean[][] selected = new boolean[size][size];
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                val += ice[r][c];

                if (!selected[r][c] && ice[r][c] > 0) {
                    queue.add(new int[] { r, c });
                    selected[r][c] = true;
                    iceSize = 1;

                    while (!queue.isEmpty()) {
                        nowIce = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            nextR = nowIce[0] + dR[i];
                            nextC = nowIce[1] + dC[i];

                            if (nextR < 0 || nextR >= size || nextC < 0 || nextC >= size) {
                                continue;
                            }

                            if (!selected[nextR][nextC] && ice[nextR][nextC] > 0) {
                                queue.add(new int[] { nextR, nextC });
                                selected[nextR][nextC] = true;
                                iceSize++;
                            }
                        }
                    }

                    maxIceSize = Math.max(maxIceSize, iceSize);
                }
            }
        }

        sb.append(String.format("%d\n%d\n", val, maxIceSize));
    }
}
