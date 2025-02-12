import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int res;
	static int row, col, time;
	static int[][][] map;
	static Queue<int[]> wait = new ArrayDeque<>();
	static Queue<int[]> act = new ArrayDeque<>();
	static Queue<int[]> create = new ArrayDeque<>();
	
	static int[] dR = {0, 1, 0, -1};
	static int[] dC = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			setInfo();
			
			getRes();
			
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void setInfo() throws Exception {
		int life;
		
		wait.clear();
		act.clear();
		create.clear();
		
		st = new StringTokenizer(br.readLine().trim());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		map = new int[3][row + 2 * time][col + 2 * time];
		
		for(int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int c = 0; c < col; c++) {
				life = Integer.parseInt(st.nextToken());
				
				if(life > 0 ) {
					map[0][r + time][c + time] = life;
					map[1][r + time][c + time] = life;
					wait.add(new int[] {r + time, c + time});
				}
				
			}
		}
	}
	
	static void getRes() {
		/*
		 * 활성화 되면
		 * 다음 사이클에 세포 증식됨 + 현재 세포는 life 1 감소
		 */
		int len;
		int[] now;
		int nowR, nowC;
		int nextR, nextC;
		
		for(int t = 0; t < time; t++) {
			// 활성 세포
			len = act.size();
			
			for(int c = 0; c < len; c++) {
				now = act.remove();
				nowR = now[0];
				nowC = now[1];
				
				map[2][nowR][nowC]--;
				
				// 활성화 후 1시간 = 증식
				if(map[2][nowR][nowC] == map[0][nowR][nowC] - 1) {
					for(int i = 0; i < 4; i++) {
						nextR = nowR + dR[i];
						nextC = nowC + dC[i];
						
						// 아직 증식하지 않은 배양판
						if(map[1][nextR][nextC] == 0 && map[2][nextR][nextC] == 0) {
							if(map[0][nextR][nextC] == 0) {
								create.add(new int[] {nextR, nextC});
							}
							
							map[0][nextR][nextC] = Math.max(map[0][nextR][nextC], map[0][nowR][nowC]);
						}
					}
				}
				
				// 0보다 크면 다음 사이클로
				if(map[2][nowR][nowC] > 0) {
					act.add(now);
				}
				// 0이면 죽은 세포
				else if(map[2][nowR][nowC] == 0) {
					map[2][nowR][nowC]--;
				}
			}
			
			// 대기 상태
			len = wait.size();
			
			for(int c = 0; c < len; c++) {
				now = wait.remove();
				nowR = now[0];
				nowC = now[1];
				
				map[1][nowR][nowC]--;
				
				if(map[1][nowR][nowC] > 0) {
					wait.add(now);
				}
				
				// 활성화 시작
				if(map[1][nowR][nowC] == 0) {
					map[1][nowR][nowC]--;
					map[2][nowR][nowC] = map[0][nowR][nowC];
					act.add(now);
				}
				
				
			}
			
			for(int[] newC : create) {
				map[1][newC[0]][newC[1]] = map[0][newC[0]][newC[1]];
				wait.add(newC);
			}
			create.clear();
			
//			if(t < 2) {
//				System.out.println((t + 1) + "시간 경과");
//				
//				for(int r = 0; r < row + 2 * time; r++) {
//					for(int c = 0; c < col + 2 * time; c++) {
//						if(map[1][r][c] != 0) {
//							System.out.printf("%2d ", map[1][r][c]);
//						} else {
//							System.out.printf("   ");
//						}
//					}
//					System.out.println();
//				}
//				System.out.println();
//				
//				for(int r = 0; r < row + 2 * time; r++) {
//					for(int c = 0; c < col + 2 * time; c++) {
//						if(map[2][r][c] != 0) {
//							System.out.printf("%2d ", map[2][r][c]);
//						} else {
//							System.out.printf("   ");
//						}
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
		}
		
		res = wait.size() + act.size();
	}

}
