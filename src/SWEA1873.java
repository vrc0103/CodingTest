/*
 * .	평지(전차가 들어갈 수 있다.)
 * *	벽돌로 만들어진 벽
 * #	강철로 만들어진 벽
 * -	물(전차는 들어갈 수 없다.)
 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
 */

import java.io.*;
import java.util.*;

public class SWEA1873 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nTestCase;
    static int nHeight, nWidth;
    static int nInputLen;
    static int[] nUserLoc = new int[2];
    static char cTankState;
    static char[] cTank = { '^', 'v', '<', '>' };
    static char[][] cMap;
    static String sTmp;

    static void ShotBomb() {
        int LocX = nUserLoc[0];
        int LocY = nUserLoc[1];

        switch (cTankState) {
            case '^':
                while (LocX > 0) {
                    if (cMap[LocX - 1][LocY] == '.' || cMap[LocX - 1][LocY] == '-') { // 평지나 물
                        LocX--; // 통과
                    } else if (cMap[LocX - 1][LocY] == '*') { // 돌벽
                        cMap[LocX - 1][LocY] = '.'; // 부수고 평지로
                        break;
                    } else { // 강철벽은 아무 변화 없음
                        break;
                    }
                }
                break;
            case 'v':
                while (LocX < nHeight - 1) {
                    if (cMap[LocX + 1][LocY] == '.' || cMap[LocX + 1][LocY] == '-') { // 평지나 물
                        LocX++; // 통과
                    } else if (cMap[LocX + 1][LocY] == '*') { // 돌벽
                        cMap[LocX + 1][LocY] = '.'; // 부수고 평지로
                        break;
                    } else { // 강철벽은 아무 변화 없음
                        break;
                    }
                }
                break;
            case '<':
                while (LocY > 0) {
                    if (cMap[LocX][LocY - 1] == '.' || cMap[LocX][LocY - 1] == '-') { // 평지나 물
                        LocY--; // 통과
                    } else if (cMap[LocX][LocY - 1] == '*') { // 돌벽
                        cMap[LocX][LocY - 1] = '.'; // 부수고 평지로
                        break;
                    } else { // 강철벽은 아무 변화 없음
                        break;
                    }
                }
                break;
            case '>':
                while (LocY < nWidth - 1) {
                    if (cMap[LocX][LocY + 1] == '.' || cMap[LocX][LocY + 1] == '-') { // 평지나 물
                        LocY++; // 통과
                    } else if (cMap[LocX][LocY + 1] == '*') { // 돌벽
                        cMap[LocX][LocY + 1] = '.'; // 부수고 평지로
                        break;
                    } else { // 강철벽은 아무 변화 없음
                        break;
                    }
                }
                break;
        }
    }

    static void RefreshMap(char cCmd) {
        switch (cCmd) {
            case 'U':
                if (nUserLoc[0] > 0 && cMap[nUserLoc[0] - 1][nUserLoc[1]] == '.') { // 맨 위가 아니고, 위가 평지인 경우
                    cMap[nUserLoc[0]][nUserLoc[1]] = '.'; // 현재 위치를 평지로 바꾸고 위로 이동
                    nUserLoc[0]--;
                }
                cTankState = '^';
                cMap[nUserLoc[0]][nUserLoc[1]] = cTankState; // 전차는 위를 보는 상태
                break;
            case 'D':
                if (nUserLoc[0] < nHeight - 1 && cMap[nUserLoc[0] + 1][nUserLoc[1]] == '.') { // 맨 아래가 아니고, 아래가 평지인 경우
                    cMap[nUserLoc[0]][nUserLoc[1]] = '.'; // 현재 위치를 평지로 바꾸고 아래로 이동
                    nUserLoc[0]++;
                }
                cTankState = 'v';
                cMap[nUserLoc[0]][nUserLoc[1]] = cTankState; // 전차는 아래를 보는 상태
                break;
            case 'L':
                if (nUserLoc[1] > 0 && cMap[nUserLoc[0]][nUserLoc[1] - 1] == '.') { // 맨 왼쪽이 아니고, 왼쪽이 평지인 경우
                    cMap[nUserLoc[0]][nUserLoc[1]] = '.'; // 현재 위치를 평지로 바꾸고 왼쪽으로 이동
                    nUserLoc[1]--;
                }
                cTankState = '<';
                cMap[nUserLoc[0]][nUserLoc[1]] = cTankState; // 전차는 왼쪽을 보는 상태
                break;
            case 'R':
                if (nUserLoc[1] < nWidth - 1 && cMap[nUserLoc[0]][nUserLoc[1] + 1] == '.') { // 맨 오른쪽이 아니고, 오른쪽이 평지인 경우
                    cMap[nUserLoc[0]][nUserLoc[1]] = '.'; // 현재 위치를 평지로 바꾸고 오른쪽으로 이동
                    nUserLoc[1]++;
                }
                cTankState = '>';
                cMap[nUserLoc[0]][nUserLoc[1]] = cTankState; // 전차는 오른쪽을 보는 상태
                break;
            case 'S':
                ShotBomb();
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        nTestCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nTestCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            nHeight = Integer.parseInt(st.nextToken());
            nWidth = Integer.parseInt(st.nextToken());
            cMap = new char[nHeight][nWidth];

            for (int row = 0; row < nHeight; row++) { // Map 구현
                sTmp = br.readLine().trim();

                for (int col = 0; col < nWidth; col++) {
                    cMap[row][col] = sTmp.charAt(col);

                    for (char tnk : cTank) {
                        if (cMap[row][col] == tnk) { // 현재 전차 위치 기록
                            nUserLoc[0] = row;
                            nUserLoc[1] = col;
                            cTankState = tnk;
                        }
                    }
                }
            }

            nInputLen = Integer.parseInt(br.readLine().trim());
            sTmp = br.readLine().trim();
            for (int i = 0; i < nInputLen; i++) {
                RefreshMap(sTmp.charAt(i));
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < nHeight; i++) {
                sb.append(cMap[i]).append("\n");
            }

            System.out.print(sb);
            sb = new StringBuilder();
        }

        // System.out.print(sb);
    }
}
