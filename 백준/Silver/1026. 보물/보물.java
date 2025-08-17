import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;

    static int res;
    static int num;
    static int[] arrayA, arrayB;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        setInfo();

        getRes();

        System.out.println(res);
    }

    static void setInfo() throws Exception {
        num = Integer.parseInt(br.readLine().trim());
        arrayA = new int[num];
        arrayB = new int[num];

        StringTokenizer st1 = new StringTokenizer(br.readLine().trim());
        StringTokenizer st2 = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < num; i++) {
            arrayA[i] = Integer.parseInt(st1.nextToken());
            arrayB[i] = Integer.parseInt(st2.nextToken());
        }
    }

    static void getRes() {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        for (int i = 0; i < num; i++) {
            res += arrayA[i] * arrayB[num - 1 - i];
        }
    }
}
