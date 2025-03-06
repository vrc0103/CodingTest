import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int num, rel;
    static int[] target;
    static Family[] family;

    static class Family {
        int parent;
        ArrayList<Integer> child;

        public Family() {
            this.parent = 0;
            this.child = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        family = new Family[num + 1];

        for(int i = 1; i <= num; i++) {
            family[i] = new Family();
        }

        st = new StringTokenizer(br.readLine().trim());
        target = new int[2];
        target[0] = Integer.parseInt(st.nextToken());
        target[1] = Integer.parseInt(st.nextToken());

        rel = Integer.parseInt(br.readLine().trim());

        while(rel-- > 0) {
            st = new StringTokenizer(br.readLine().trim());

            int par = Integer.parseInt(st.nextToken());
            int chd = Integer.parseInt(st.nextToken());

            family[par].child.add(chd);
            family[chd].parent = par;
        }
    }

    static void getRes() {
        ArrayList<Integer> par1 = new ArrayList<>();
        ArrayList<Integer> par2 = new ArrayList<>();

        while(family[target[0]] != null) {
            par1.add(target[0]);
            target[0] = family[target[0]].parent;
        }

        while (family[target[1]] != null) {
            par2.add(target[1]);
            target[1] = family[target[1]].parent;
        }

        // System.out.println(par1.toString());
        // System.out.println(par2.toString());

        for(int i = 0; i < par1.size(); i++) {
            for(int j = 0; j < par2.size(); j++) {
                if(par1.get(i) == par2.get(j)) {
                    res = i + j;
                    return;
                }

                if(i == par1.size() - 1 && j == par2.size() - 1) {
                    res = -1;
                }
            }
        }
    }
}
