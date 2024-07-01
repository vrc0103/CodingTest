import java.io.*;

public class SWEA2005 {
    public static void main(String[] args) throws IOException {
        int nT, nN;
        int[][] nPscTri;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= nT; test++) {
            nN = Integer.parseInt(br.readLine().trim());
            nPscTri = new int[nN][];

            for (int i = 0; i < nN; i++) { // 파스칼의 삼각형 구현
                nPscTri[i] = new int[i + 1];

                for (int j = 0; j < i + 1; j++) {
                    if (i < 2 || j == 0 || j == i) { // 첫 2줄과 맨앞, 맨뒤 인자는 1
                        nPscTri[i][j] = 1;
                    } else {
                        nPscTri[i][j] = nPscTri[i - 1][j - 1] + nPscTri[i - 1][j];
                    }
                }
            }

            System.out.println("#" + test); // 출력
            for (int i = 0; i < nN; i++) {
                for (int j = 0; j < i + 1; j++) {
                    System.out.print(nPscTri[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
