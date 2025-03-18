import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num;
    static int[] card;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        getRes();

        System.out.print(res);
    }

    static void getRes() throws Exception {
        ArrayDeque<Integer> card = new ArrayDeque<>();

        num = Integer.parseInt(br.readLine().trim());

        for(int i = 1; i <= num; i++) {
            card.offer(i);
        }

        while(true) {
            if(card.size() == 1) {
                break;
            } else {
                card.poll();
            }

            if (card.size() == 1) {
                break;
            } else {
                card.offer(card.poll());
            }
        }

        res = card.poll();
    }
}
