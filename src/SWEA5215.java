/*
 * 제한 칼로리를 넘지 않는 선에서
 * 맛 수치의 최댓값 계산
 */

import java.io.*;
import java.util.*;

public class SWEA5215 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int numOfMat;
    static int calLim;
    static int maxTaste;
    static boolean[] selected;
    static Ingredients[] ingr;

    static class Ingredients implements Comparable<Ingredients> { // Comparable을 상속받으면 Collections.sort를 이용해 정렬 가능
        int tasteValue;
        int calorie;

        public Ingredients(int tasteValue, int calorie) {
            this.tasteValue = tasteValue;
            this.calorie = calorie;
        }

        int getTasteValue() {
            return this.tasteValue;
        }

        int getCalorie() {
            return this.calorie;
        }

        // Override

        public int compareTo(Ingredients ingr) { // Collections.sort의 정렬 기준 변수
            return this.getCalorie() - ingr.getCalorie();
            // 오름차순 : 호출 객체 - 인자 객체
            // 내림차순 : 인자 객체 - 호출 객체
        }

    }

    static void getValues() throws IOException {
        maxTaste = 0;

        st = new StringTokenizer(br.readLine().trim());
        numOfMat = Integer.parseInt(st.nextToken());
        calLim = Integer.parseInt(st.nextToken());

        ingr = new Ingredients[numOfMat];
        selected = new boolean[numOfMat];

        for (int i = 0; i < numOfMat; i++) {
            st = new StringTokenizer(br.readLine().trim());
            ingr[i] = new Ingredients(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(ingr);
    }

    static void getMaxValue(int startIdx, int sumOfCal) {
        if (sumOfCal > calLim) { // 칼로리 총합이 제한보다 커지면 재귀 종료
            return;
        } else { // 칼로리 제한보다 낮은 선에서 맛의 최댓값 계산
            int taste = 0;

            for (int sel = 0; sel < numOfMat; sel++) {
                if (selected[sel]) {
                    taste += ingr[sel].getTasteValue();
                }
            }

            maxTaste = Math.max(taste, maxTaste);
        }

        for (int i = startIdx; i < numOfMat; i++) {
            selected[i] = true;
            sumOfCal += ingr[i].getCalorie();
            getMaxValue(i + 1, sumOfCal); // 선택한 재료에 대한 칼로리를 더하여 재귀호출
            selected[i] = false;
            sumOfCal -= ingr[i].getCalorie(); // 다음 인덱스에 대한 확인을 위해 다시 감소시켜둠
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            getValues();

            getMaxValue(0, 0);

            sb.append(String.format("#%d %d\n", tc, maxTaste));
        }

        System.out.print(sb);
    }
}
