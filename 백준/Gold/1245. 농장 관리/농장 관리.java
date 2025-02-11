import java.io.*;
import java.util.*;

public class Main {
	static int res;
	static int row, col;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dR = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dC = {1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		// 정보
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().trim());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new int[row][col];
		visited = new boolean[row][col];
		
		for(int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int c = 0; c < col; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 풀이
		for(int r = 0; r < row; r++) {
			for(int c = 0; c < col; c++) {
				if(!visited[r][c]) {
					visited[r][c] = true;
					
					if(isTop(r, c)) {
						res++;
					}
				}
			}
		}
		
		System.out.println(res);
	}
	
	static boolean isTop(int r, int c) {
		int nextR, nextC;
		int[] now;
		boolean judge = true;
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			now = queue.remove();

			for(int i = 0; i < 8; i++) {
				nextR = now[0] + dR[i];
				nextC = now[1] + dC[i];
				
				if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
					continue;
				}
				
				if(map[r][c] < map[nextR][nextC]) {
					judge = false;
				}
				
				if(!visited[nextR][nextC] && map[r][c] == map[nextR][nextC]) {
					visited[nextR][nextC] = true;
					
					queue.add(new int[] {nextR, nextC});
				}
			}
		}
		
		return judge;
	}

}
