/*
 * 비내림차순 : 오름차순과 유사한 개념으로, 중복된 값이 존재할 수 있음
 *  -> 추가 정렬 필요X
 */

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfTitle, numOfChar;
    static ArrayList<Title> titles = new ArrayList<>();

    static class Title {
        String title;
        int maxPower;

        public Title(String title, int maxPower) {
            this.title = title;
            this.maxPower = maxPower;
        }
    }

    public static void main(String[] args) throws IOException {
        setInfo();

        getTitles();

        System.out.print(sb.toString());
    }

    static void setInfo() throws IOException {
        String title;
        int maxPower;

        st = new StringTokenizer(br.readLine().trim());
        numOfTitle = Integer.parseInt(st.nextToken());
        numOfChar = Integer.parseInt(st.nextToken());

        // 탐색 구현이 용이하도록 최솟값 0 추가
        titles.add(new Title("TMP", 0));

        for (int i = 0; i < numOfTitle; i++) {
            st = new StringTokenizer(br.readLine().trim());
            title = st.nextToken();
            maxPower = Integer.parseInt(st.nextToken());

            // 동일한 상한선은 먼저 입력된 칭호가 선택되므로 첫 칭호만 저장
            if (i > 0 && maxPower == titles.get(titles.size() - 1).maxPower) {
                continue;
            }

            titles.add(new Title(title, maxPower));
        }
    }

    static void getTitles() throws IOException {
        int power;
        int left, right, idx;

        for (int i = 0; i < numOfChar; i++) {
            power = Integer.parseInt(br.readLine().trim());
            left = 0;
            right = titles.size() - 1;

            while (right - left > 1) {
                idx = (left + right) / 2;

                if (power <= titles.get(idx).maxPower) {
                    right = idx;
                } else {
                    left = idx;
                }
            }

            sb.append(titles.get(right).title).append("\n");
        }
    }
}
