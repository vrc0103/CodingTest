import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("Test5.txt"));
		//---------여기에 코드를 작성하세요.---------------//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[][] map = new int[20][20];
		
		for(int r = 1; r < 20; r++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int c = 1; c < 20; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int len;
		int stone;
		int nextR, nextC;
		
		int[] dR = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dC = {1, 1, 0, -1, -1, -1, 0, 1};
		
		for(int r = 1; r < 20; r++) {
			for(int c = 1; c < 20; c++) {
				stone = map[r][c];
				
				if(stone == 0) {
					continue;
				}
				
				// 연결된 돌 방향 찾기
				for(int i = 0; i < 8; i++) {
					nextR = r + dR[i];
					nextC = c + dC[i];
					
					if(nextR < 1 || nextR > 19 || nextC < 1 || nextC > 19 || map[nextR][nextC] != stone) {
						continue;
					}
					
					len = 0;
					
					// 연결된 돌 발견 시 해당 방향으로 추가 탐색
					for(int j = 0; j <= 4; j++) {
						nextR = r + j * dR[i];
						nextC = c + j * dC[i];
						
						if(nextR < 1 || nextR > 19 || nextC < 1 || nextC > 19 || map[nextR][nextC] != stone) {
							break;
						}
						
						len++;
					}
					
					// 오목 완성
					if(len == 5) {
						// 추가 돌 확인
						int sixR = nextR + dR[i];
						int sixC = nextC + dC[i];
						
						if(sixR > 0 && sixR < 20 && sixC > 0 && sixC < 20 && map[sixR][sixC] == stone) {
							len++;
						}
						
						// 반대 방향도 확인
						sixR = r + dR[(i + 4) % 8];
						sixC = c + dC[(i + 4) % 8];
						
						if(sixR > 0 && sixR < 20 && sixC > 0 && sixC < 20 && map[sixR][sixC] == stone) {
							len++;
						}
						
						// 진짜 오목
						if(len == 5) {
							sb.append(stone).append("\n");
							
							if(nextC == c) {
								sb.append(Math.min(r, nextR)).append(" ").append(c);
							} else if(nextC < c) {
								sb.append(nextR).append(" ").append(nextC);
							} else {
								sb.append(r).append(" ").append(c);
							}
							
							System.out.println(sb);
							return;
						}
					}
				}		
			}
		}
		
		// 무승부
		System.out.println("0");
	}

}
