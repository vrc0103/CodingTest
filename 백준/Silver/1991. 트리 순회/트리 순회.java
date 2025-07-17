import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int res;
    static int node;
    static char[][] tree;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        tree = new char[26][2];

        node = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < node; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int parent = st.nextToken().charAt(0) - 'A';
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree[parent][0] = left;
            tree[parent][1] = right;
        }
    }

    static void getRes() {
        preOrder(0);
        sb.append("\n");

        inOrder(0);
        sb.append("\n");

        postOrder(0);
        sb.append("\n");
    }

    static void preOrder(int now) {
        char ch = (char) ('A' + now);
        int left = tree[now][0] - 'A';
        int right = tree[now][1] - 'A';

        sb.append(ch);

        if (left > 0 && left < 26) {
            preOrder(left);
        }

        if (right > 0 && right < 26) {
            preOrder(right);
        }
    }

    static void inOrder(int now) {
        char ch = (char) ('A' + now);
        int left = tree[now][0] - 'A';
        int right = tree[now][1] - 'A';

        if (left > 0 && left < 26) {
            inOrder(left);
        }

        sb.append(ch);

        if (right > 0 && right < 26) {
            inOrder(right);
        }
    }

    static void postOrder(int now) {
        char ch = (char) ('A' + now);
        int left = tree[now][0] - 'A';
        int right = tree[now][1] - 'A';

        if (left > 0 && left < 26) {
            postOrder(left);
        }

        if (right > 0 && right < 26) {
            postOrder(right);
        }

        sb.append(ch);
    }
}
