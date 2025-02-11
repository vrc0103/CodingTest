import java.io.*;
import java.util.*;

public class Main {
    /*
     * 1. 등장 횟수 오름차순
     * 2. 숫자 오름차순
     * 3. 숫자, 등장 횟수 순으로 배열에 저장
     * 4. 행, 열 중 더 큰 쪽으로 정렬하며 동일한 크기이면 행 정렬
     * 5. 배열 확장 시 빈 칸은 0으로 채우며, 0은 숫자로 취급 X
     */

    static int res;
    static int row, col, num;
    static boolean sortR;
    static int[][] arr;

    static class Num implements Comparable<Num> {
        int key;
        int count;

        public Num(int key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(Num o) {
            if (this.count != o.count) {
                return this.count - o.count;
            } else {
                return this.key - o.key;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());

        arr = new int[3][3];
        for (int r = 0; r < 3; r++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int c = 0; c < 3; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        sortR = true;

        for (int t = 0; t <= 100; t++) {
            if (arr.length > row - 1 && arr[0].length > col - 1 && arr[row - 1][col - 1] == num) {
                System.out.println(t);

                return;
            }

            sort(sortR);
        }

        System.out.println(-1);
    }

    static void sort(boolean jdg) {
        int maxLen = 0;
        int lenL = jdg ? arr.length : arr[0].length; // 긴 쪽
        int lenS = jdg ? arr[0].length : arr.length; // 짧은 쪽

        // 정렬 방향으로 숫자 개수 계산
        @SuppressWarnings("unchecked")
        HashMap<Integer, Integer>[] count = new HashMap[lenL];

        for (int i = 0; i < lenL; i++) {
            count[i] = new HashMap<Integer, Integer>();

            for (int j = 0; j < lenS; j++) {
                if (jdg) {
                    count[i].put(arr[i][j], count[i].getOrDefault(arr[i][j], 0) + 1);
                } else {
                    count[i].put(arr[j][i], count[i].getOrDefault(arr[j][i], 0) + 1);
                }
            }

            count[i].remove(0);

            maxLen = Math.max(maxLen, count[i].size() * 2);
        }

        // 배열 정렬
        int[][] nArr;
        ArrayList<Num> tmpArr;

        if (jdg) {
            nArr = new int[lenL][maxLen];
        } else {
            nArr = new int[maxLen][lenL];
        }

        for (int i = 0; i < lenL && i < 100; i++) {
            tmpArr = new ArrayList<>();

            for (int key : count[i].keySet()) {
                // 0 제외
                if (key > 0) {
                    tmpArr.add(new Num(key, count[i].get(key)));
                }
            }

            Collections.sort(tmpArr);

            for (int j = 0; j < tmpArr.size() && j < 50; j++) {
                if (jdg) {
                    nArr[i][j * 2] = tmpArr.get(j).key;
                    nArr[i][j * 2 + 1] = tmpArr.get(j).count;
                } else {
                    nArr[j * 2][i] = tmpArr.get(j).key;
                    nArr[j * 2 + 1][i] = tmpArr.get(j).count;
                }
            }
        }

        // 배열 및 정렬 방향 갱신
        arr = nArr;
        sortR = nArr.length >= nArr[0].length ? true : false;
    }
}