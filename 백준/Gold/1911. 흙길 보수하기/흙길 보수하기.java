import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num, len;
    static List<Hole> hole;

    static class Hole implements Comparable<Hole> {
        int start;
        int end;

        public Hole(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Hole o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());

        hole = new ArrayList<>();
        for(int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            hole.add(new Hole(start, end));
        }

        Collections.sort(hole);
    }

    static void getRes() {
        int loc = 0;

        for(int i = 0; i < num; i++) {
            loc = Math.max(loc, hole.get(i).start);

            while(loc < hole.get(i).end) {
                loc += len;
                res++;
            }
        }
    }
}
