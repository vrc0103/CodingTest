import java.io.*;
import java.util.*;

public class SWEA3289 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCase;
    static int numOfOper;
    static int size;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append(String.format("#%d ", tc));

            getSubset();
        }

        System.out.print(sb.toString());
    }

    static void getSubset() throws IOException {
        int oper;
        int num1, num2;

        st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken()) + 1; // 1 ~ N
        numOfOper = Integer.parseInt(st.nextToken());

        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        for (int op = 0; op < numOfOper; op++) {
            st = new StringTokenizer(br.readLine());

            oper = Integer.parseInt(st.nextToken());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());

            // Union
            if (oper == 0) {
                union(num1, num2);
            }

            // Find
            else {
                if (getParent(num1) == getParent(num2)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
        }

        sb.append("\n");
    }

    static void union(int num1, int num2) {
        int parent1 = getParent(num1);
        int parent2 = getParent(num2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }

    static int getParent(int num) {
        if (parent[num] == num) {
            return num;
        }

        return parent[num] = getParent(parent[num]);
    }
}
