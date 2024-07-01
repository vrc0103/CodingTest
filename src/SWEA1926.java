import java.io.*;

public class SWEA1926 {
    public static void main(String[] args) throws IOException {
        int nN;
        int nTmp;
        int n3Check;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nN = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i <= nN; i++) {
            n3Check = 0;
            nTmp = i;
            while (nTmp > 0) {
                if (nTmp % 10 % 3 == 0 && nTmp % 10 != 0) { // 자릿수 하나씩 올려가며 3의 배수가 있는지 판단, 0은 3의 배수 X
                    n3Check = 1;
                    System.out.print("-");
                }
                nTmp /= 10;
            }
            if (n3Check == 0) { // 각 자리에 3의 배수가 하나도 없는 경우
                System.out.print(i);
            }
            System.out.print(" ");
        }
    }
}
