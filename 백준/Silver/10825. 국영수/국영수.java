import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Score implements Comparable<Score> {
        String name;
        int kor;
        int eng;
        int math;

        public Score(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Score o) {
            if (this.kor == o.kor) {
                if (this.eng == o.eng) {
                    if (this.math == o.math) {
                        return this.name.compareTo(o.name);
                    }

                    return o.math - this.math;
                }

                return this.eng - o.eng;
            }

            return o.kor - this.kor;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        int num = Integer.parseInt(br.readLine().trim());
        ArrayList<Score> list = new ArrayList<>();

        while (num-- > 0) {
            st = new StringTokenizer(br.readLine().trim());

            String name = st.nextToken();
            int kor = Integer.parseInt(st.nextToken());
            int eng = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            list.add(new Score(name, kor, eng, math));
        }

        Collections.sort(list);

        for (Score score : list) {
            sb.append(score.name).append("\n");
        }
    }
}
