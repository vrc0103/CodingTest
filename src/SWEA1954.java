//첫 줄에 케이스 갯수 T
//각 줄에 달팽이 크기 N

import java.io.*;

public class SWEA1954 {
    static int nSnale = 0; // 달팽이 숫자 카운트

    public static void implSnale(int[][] Snale, int nCount) {
        int nSize = Snale.length; // N은 달팽이 크기
        int loopCount = nCount / 4;// 진행 횟수
        int RDLU = nCount % 4;// 숫자 증가 진행 방향 : 오른쪽 아래 왼쪽 위

        if (nSnale == nSize * nSize) { // 재귀 탈출 : 카운트가 달팽이 크기의 제곱이면 가득 찬 상태
            nSnale = 0; // 다음 테스트를 위한 카운트 초기화
            return;
        }

        switch (RDLU) { // 진행방향
            case 0: // Right
                for (int i = loopCount; i < nSize - loopCount; i++) {
                    Snale[loopCount][i] = ++nSnale;
                }
                break;
            case 1: // Down
                for (int i = loopCount + 1; i < nSize - loopCount; i++) { // nSize -1 = 배열 끝 값
                    Snale[i][nSize - 1 - loopCount] = ++nSnale;
                }
                break;
            case 2: // Left
                for (int i = nSize - 1 - loopCount; i > loopCount; i--) {
                    Snale[nSize - 1 - loopCount][i - 1] = ++nSnale;
                }
                break;
            case 3: // Up
                for (int i = nSize - 1 - loopCount; i > loopCount + 1; i--) {
                    Snale[i - 1][loopCount] = ++nSnale;
                }
                break;
        }
        implSnale(Snale, ++nCount); // 재귀
    }

    public static void main(String[] args) throws IOException {
        int nT;
        int nScale;
        int[][] Snale;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        nT = Integer.parseInt(bf.readLine()); // 첫 줄 입력을 정수화 해서 T에 저장

        for (int i = 0; i < nT; i++) {
            nScale = Integer.parseInt(bf.readLine()); // 다음 줄에 입력된 달팽이 크기
            Snale = new int[nScale][nScale]; // 달팽이 크기에 해당하는 2차원 배열(선언 시 기본은 0으로 초기화됨)
            implSnale(Snale, 0);

            System.out.println("#" + (i + 1));
            for (int row = 0; row < nScale; row++) {
                for (int col = 0; col < nScale; col++) {
                    System.out.print(Snale[row][col] + " ");
                }
                System.out.println();
            }
        }
    }
}
