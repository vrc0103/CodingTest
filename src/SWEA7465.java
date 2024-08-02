import java.io.*;
import java.util.*;

public class SWEA7465 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int population;
    static int acquaintance;
    static int[] leader;

    public static void main(String[] args) throws IOException {
        getGroup();

        System.out.print(sb.toString());
    }

    static void getGroup() throws IOException {
        int people1, people2;
        int group;

        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            population = Integer.parseInt(st.nextToken()) + 1; // 1 ~ N이므로 +1
            acquaintance = Integer.parseInt(st.nextToken());

            leader = new int[population];
            for (int ld = 1; ld < population; ld++) { // Union-Find 배열 초기화
                leader[ld] = ld;
            }

            for (int ac = 0; ac < acquaintance; ac++) { // Union 수행
                st = new StringTokenizer(br.readLine());
                people1 = Integer.parseInt(st.nextToken());
                people2 = Integer.parseInt(st.nextToken());

                setReader(people1, people2);
            }

            group = 0;
            for (int pp = 1; pp < population; pp++) { // 그룹장인 사람만 카운트
                if (leader[pp] == pp) {
                    group++;
                }
            }

            sb.append(String.format("#%d %d\n", tc, group));
        }
    }

    static void setReader(int people1, int people2) {
        int leader1 = findLeader(people1);
        int leader2 = findLeader(people2);

        if (leader1 > leader2) {
            leader[leader1] = leader2;
        } else {
            leader[leader2] = leader1;
        }
    }

    static int findLeader(int people) {
        if (leader[people] == people) {
            return people;
        }

        return leader[people] = findLeader(leader[people]);
    }
}
