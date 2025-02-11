import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int row, col;
    static String input;
    static char[][] encrypted;

    public static void main(String[] args) throws IOException {
        decryption();

        System.out.print(sb.toString());
    }

    static void decryption() throws IOException {
        int len;
        int rTmp, cTmp;
        int idx = 0;

        input = br.readLine().trim();
        len = input.length();
        rTmp = 1;
        cTmp = len;

        while (rTmp <= cTmp) {
            if (len % rTmp == 0) {
                row = rTmp;
                col = cTmp;
            }

            rTmp++;
            cTmp = len / rTmp;
        }

        encrypted = new char[row][col];

        // 열 우선으로 배열 저장
        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                encrypted[r][c] = input.charAt(idx);
                idx++;
            }
        }

        // 행 우선으로 배열 읽기
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                sb.append(encrypted[r][c]);
            }
        }

        sb.append("\n");
    }
}
