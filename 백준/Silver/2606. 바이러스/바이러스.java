import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int num;
    static int cnt;
    static int[] link;

    public static void main(String[] args) throws IOException {
        getRes();

        System.out.println(res);
    }

    static void getRes() throws IOException {
        int from, to;

        num = Integer.parseInt(br.readLine().trim());
        cnt = Integer.parseInt(br.readLine().trim());

        // Union 배열 초기화
        link = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            link[i] = i;
        }

        // 정보를 입력받으면서 Union 수행
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            setUnion(from, to);
        }

        // 1번을 제외한 바이러스 컴퓨터
        for (int i = 2; i <= num; i++) {
            if (getParent(i) == 1) {
                res++;
            }
        }
    }

    static void setUnion(int cpt1, int cpt2) {
        int par1 = getParent(cpt1);
        int par2 = getParent(cpt2);

        if (par1 > par2) {
            link[par1] = par2;
        } else {
            link[par2] = par1;
        }
    }

    static int getParent(int cpt) {
        // 값이 자기 자신 = root
        if (link[cpt] == cpt) {
            return cpt;
        }

        return link[cpt] = getParent(link[cpt]);
    }
}
