import java.io.*;
import java.util.*;

public class SWEA1230 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        for (int tc = 1; tc <= 10; tc++) {
            sb.append("#").append(tc).append(" ");

            getEnc();
        }

        System.out.print(sb);
    }

    static void getEnc() throws Exception {
        int len = Integer.parseInt(br.readLine().trim());
        ArrayList<Integer> enc = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < len; i++) {
            enc.add(Integer.parseInt(st.nextToken()));
        }

        int num = Integer.parseInt(br.readLine().trim());
        char oper;
        int idx, count;

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            oper = st.nextToken().charAt(0);

            if (oper == 'I') {
                idx = Integer.parseInt(st.nextToken());
                count = Integer.parseInt(st.nextToken());

                for (int j = 0; j < count; j++) {
                    enc.add(idx + j, Integer.parseInt(st.nextToken()));
                }
            } else if (oper == 'D') {
                idx = Integer.parseInt(st.nextToken());
                count = Integer.parseInt(st.nextToken());

                for (int j = 0; j < count; j++) {
                    enc.remove(idx);
                }
            } else if (oper == 'A') {
                count = Integer.parseInt(st.nextToken());

                for (int j = 0; j < count; j++) {
                    enc.add(Integer.parseInt(st.nextToken()));
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            sb.append(enc.get(i)).append(" ");
        }
        sb.append("\n");
    }
}
