import java.io.*;

public class BOJ2607 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int res = 0;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int num = Integer.parseInt(br.readLine().trim());
        int count1, count2;
        char[] base = br.readLine().trim().toCharArray();
        char[] word;
        boolean[] wordComp1, wordComp2;
        String input;

        for (int i = 1; i < num; i++) {
            input = br.readLine().trim();
            count1 = count2 = 0;

            // 단어 길이 차이가 2 이상이면 비슷하지 않음
            if (Math.abs(base.length - input.length()) > 1) {
                continue;
            }

            word = input.toCharArray();
            wordComp1 = new boolean[base.length];
            wordComp2 = new boolean[word.length];

            // 단어 구성 비교
            for (int w1 = 0; w1 < base.length; w1++) {
                for (int w2 = 0; w2 < word.length; w2++) {
                    if (base[w1] == word[w2] && !wordComp2[w2]) {
                        wordComp1[w1] = true;
                        wordComp2[w2] = true;
                        break;
                    }
                }
            }

            // 서로 다른 스펠링 개수 계산
            for (int w = 0; w < base.length; w++) {
                if (!wordComp1[w]) {
                    count1++;
                }
            }

            for (int w = 0; w < word.length; w++) {
                if (!wordComp2[w]) {
                    count2++;
                }
            }

            // 겹치지 않은 스펠링이 두 단어 모두 1개 이하여야 비슷한 단어로 판정
            if (count1 <= 1 && count2 <= 1) {
                res++;
            }
        }
    }
}
