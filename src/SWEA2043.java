import java.util.*;

public class SWEA2043 {
    public static void main(String[] args) {
        int P, K, res;
        Scanner sc = new Scanner(System.in);

        P = sc.nextInt();
        K = sc.nextInt();

        res = P - K + 1;
        System.out.println(res);

        sc.close();
    }
}
