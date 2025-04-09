import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int res;
    static int people, party;
    static int knowNum;
    static int[] root;
    static List<List<Integer>> participant;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        people = Integer.parseInt(st.nextToken());
        party = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        root = new int[people + 1];
        for(int i = 0; i <= people; i++) {
            root[i] = i;
        }

        knowNum = Integer.parseInt(st.nextToken());
        if(knowNum > 0) {
            for(int i = 0; i < knowNum; i++) {
                root[Integer.parseInt(st.nextToken())] = 0;
            }
        }

        participant = new ArrayList<>();
        for(int i = 0; i < party; i++) {
            participant.add(new ArrayList<Integer>());

            List<Integer> ptcpt = participant.get(i);

            st = new StringTokenizer(br.readLine().trim());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j = 0; j < cnt; j++) {
                ptcpt.add(Integer.parseInt(st.nextToken()));

                if(j > 0) {
                    union(ptcpt.get(j - 1), ptcpt.get(j));
                }
            }
        }
    }

    static void getRes() {
        if(knowNum == 0) {
            res = party;
            return;
        }

        loop: for(int i = 0; i < party; i++) {
            List<Integer> ptcpt = participant.get(i);

            for(int j = 0; j < ptcpt.size(); j++) {
                if(find(ptcpt.get(j)) == 0) {
                    continue loop;
                }
            }

            res++;
        }
    }

    static void union(int num1, int num2) {
        int p1 = find(num1);
        int p2 = find(num2);

        if(p1 > p2) {
            root[p1] = p2;
        } else {
            root[p2] = p1;
        }
    }

    static int find(int num) {
        if(root[num] == num) {
            return num;
        }

        return root[num] = find(root[num]);
    }
}
