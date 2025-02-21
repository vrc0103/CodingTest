import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int res;
    static int ing, cnt;
    static boolean[] checked;
    static ArrayList<ArrayList<Integer>> imp;

    public static void main(String[] args) throws Exception {
        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1 ; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        int n1, n2;

        res = 0;
        imp = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        ing = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= ing; i++) {
            imp.add(new ArrayList<>());
        }

        for(int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            imp.get(n1).add(n2);
            imp.get(n2).add(n1);
        }

        for(int i = 1; i <= ing; i++) {
            Collections.sort(imp.get(i));
        }
    }

    static void getRes() {
        checked = new boolean[ing + 1];

        comb(1);
    }

    static void comb(int idx) {
        if(idx == ing + 1) {
            res++;
            // for(int i = 1; i <= ing; i++) {
            //     System.out.printf("%d ", checked[i] ? 1 : 0);
            // }
            // System.out.println();

            return;
        }

        int now = idx;
        boolean flag = true;

        while(--now >= 0) {
            if(checked[now] && Collections.binarySearch(imp.get(idx), now) >= 0) {
                flag = false;
                break;
            }
        }

        if(flag) {
            checked[idx] = true;
            comb(idx + 1);
        }

        checked[idx] = false;
        comb(idx + 1);
    }
}
