import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int[] score;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.println(sb.toString());
    }

    static void getRes() throws Exception {
        int[] score = new int[8];
        int[] sorted = new int[8];
        int[] correct = new int[5];

        for(int i = 0; i < 8; i++) {
            score[i] = Integer.parseInt(br.readLine().trim());
            sorted[i] = score[i];
        }

        Arrays.sort(sorted);
        
        for(int i = 7; i >= 3; i--) {
            for(int j = 0; j < 8; j++) {
                if(sorted[i] == score[j]) {
                    res += sorted[i];
                    correct[7 - i] = j + 1;
                }
            }
        }

        Arrays.sort(correct);

        sb.append(res).append("\n");
        for(int i = 0; i < 5; i++) {
            sb.append(correct[i]).append(" ");
        }
    }
}
