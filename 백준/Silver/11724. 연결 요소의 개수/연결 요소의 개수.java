import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int vtx, edge;
    static int[] union;
    static boolean[] root;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int from, to;
        int parent;

        st = new StringTokenizer(br.readLine().trim());
        vtx = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        union = new int[vtx + 1];
        root = new boolean[vtx + 1];
        for (int i = 1; i <= vtx; i++) {
            union[i] = i;
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine().trim());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            setUnion(from, to);
        }

        for (int i = 1; i <= vtx; i++) {
            parent = getParent(i);

            // 처음 등장한 root이면 결과 1 증가
            if (!root[parent]) {
                root[parent] = true;
                res++;
            }
        }
    }

    static void setUnion(int num1, int num2) {
        int par1 = getParent(num1);
        int par2 = getParent(num2);

        if (par1 > par2) {
            union[par1] = par2;
        } else {
            union[par2] = par1;
        }
    }

    static int getParent(int num) {
        if (union[num] == num) {
            return num;
        }

        return union[num] = getParent(union[num]);
    }
}
