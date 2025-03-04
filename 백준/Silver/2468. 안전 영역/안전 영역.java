import java.io.*;
import java.util.*;

public class Main {
	static int res = 1;
	static int size;
	static int max;
	static int[][] map;
	
	static int[] dR = {0, 1, 0, -1};
	static int[] dC = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		size = Integer.parseInt(br.readLine().trim());
		map = new int[size][size];
		
		for(int r = 0; r < size; r++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int c = 0; c < size; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				max = Math.max(max, map[r][c]);
			}
		}
		
		for(int i = 1; i < max; i++) {
			getRes(i);
		}
		
		System.out.println(res);
	}
	
	static void getRes(int num) {
		int cnt = 0;
		boolean[][] checked = new boolean[size][size];
		
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				if(map[r][c] > num && !checked[r][c]) {
					cnt++;
					ArrayDeque<int[]> queue = new ArrayDeque<>();
					
					queue.add(new int[] {r, c});
					checked[r][c] = true;
					
					while(!queue.isEmpty()) {
						int[] now = queue.remove();
						
						for(int i = 0; i < 4; i++) {
							int nextR = now[0] + dR[i];
							int nextC = now[1] + dC[i];
							
							if(nextR < 0 || nextR >= size || nextC < 0 || nextC >= size || map[nextR][nextC] <= num || checked[nextR][nextC]) {
								continue;
							}
							
							queue.add(new int[] {nextR, nextC});
							checked[nextR][nextC] = true;
						}
					}
				}
			}
		}
		
		res = Math.max(res, cnt);
	}

}
