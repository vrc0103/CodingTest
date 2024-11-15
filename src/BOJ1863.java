import java.io.*;
import java.util.*;

public class BOJ1863 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int len;
    static int[] skyLine;

    public static void main(String[] args) throws IOException {
        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws IOException {
        int height;

        len = Integer.parseInt(br.readLine().trim());
        skyLine = new int[len];

        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine().trim());
            st.nextToken();
            height = Integer.parseInt(st.nextToken());

            skyLine[i] = height;
        }
    }

    static void getRes() {
        int count = 0;
        int height;

        Integer[] heightLoc = new Integer[500001];

        for (int i = 0; i < len; i++) {
            height = skyLine[i];

            if (height == 0) {
                heightLoc[0] = i;
            }
            // 이전에 동일한 높이의 건물이 없었으면 현재 위치로 초기화
            else if (heightLoc[height] == null) {
                heightLoc[height] = i;
                count++;
            }
            // 동일한 높이의 건물이 있었으면 해당 위치까지 현재 높이보다 낮은 높이가 있는지 확인
            else {
                for (int j = i - 1; j > heightLoc[height]; j--) {
                    // 낮은 높이가 있었으면 새로운 건물
                    if (skyLine[j] < height) {
                        count++;
                        break;
                    }
                }

                // 해당 높이의 위치 갱신
                heightLoc[height] = i;
            }
        }

        res = count;
    }
}
