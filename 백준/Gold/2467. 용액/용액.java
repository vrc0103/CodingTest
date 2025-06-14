import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int num;
    static int[] value;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        value = new int[num];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void getRes() {
        int n1 = 0;
        int n2 = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < num; i++) {
            int now = value[i];
            int idx = Arrays.binarySearch(value, now * (-1));

            if (idx >= 0) {
                n1 = value[i] < value[idx] ? value[i] : value[idx];
                n2 = value[i] < value[idx] ? value[idx] : value[i];

                break;
            } else {
                int near = idx * (-1) - 1;

                int sum = near < num ? Math.abs(value[i] + value[near]) : Integer.MAX_VALUE;

                if (min > sum && near != i) {
                    min = sum;

                    n1 = value[i] < value[near] ? value[i] : value[near];
                    n2 = value[i] < value[near] ? value[near] : value[i];
                }

                sum = near > 0 ? Math.abs(value[i] + value[near - 1]) : Integer.MAX_VALUE;

                if (min > sum && near - 1 != i) {
                    min = sum;

                    n1 = value[i] < value[near - 1] ? value[i] : value[near - 1];
                    n2 = value[i] < value[near - 1] ? value[near - 1] : value[i];
                }
            }
        }

        sb.append(n1).append(" ").append(n2).append("\n");
    }
}
