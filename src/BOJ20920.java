import java.io.*;
import java.util.*;

public class BOJ20920 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int num;
    static int maxLen;
    static ArrayList<Word> dict = new ArrayList<>();
    static ArrayList<String> words = new ArrayList<>();

    static class Word implements Comparable<Word> {
        String word;
        int cnt;

        public Word(String word) {
            this.word = word;
            this.cnt = 1;
        }

        @Override
        public int compareTo(Word o) {
            // 1. 자주 등장
            if (this.cnt == o.cnt) {
                // 2. 길이가 긴 순서
                if (this.word.length() == o.word.length()) {
                    // 3. 사전순
                    return this.word.compareTo(o.word);
                }
                return o.word.length() - this.word.length();
            }
            return o.cnt - this.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        getOrder();

        System.out.print(sb.toString());
    }

    static void getOrder() throws IOException {
        String input;
        int size1, size2;

        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        maxLen = Integer.parseInt(st.nextToken());

        for (int i = 0; i < num; i++) {
            input = br.readLine().trim();

            if (input.length() < maxLen) {
                continue;
            }

            words.add(input);
        }

        // 사전순 정렬
        Collections.sort(words);

        size1 = words.size();
        dict.add(new Word(words.get(0)));

        for (int i = 1; i < size1; i++) {
            // 이번 단어가 이전 단어와 같으면 cnt 1 증가
            if (words.get(i).equals(words.get(i - 1))) {
                dict.get(dict.size() - 1).cnt++;
            }

            // 아니면 새로 추가
            else {
                dict.add(new Word(words.get(i)));
            }
        }

        // 규칙에 따라 정렬
        Collections.sort(dict);

        size2 = dict.size();
        for (int i = 0; i < size2; i++) {
            sb.append(dict.get(i).word).append("\n");
        }
    }
}
