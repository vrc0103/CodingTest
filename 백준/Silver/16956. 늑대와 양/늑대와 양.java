import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		// 정보
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		char[][] map = new char[row][];
		String input;
		
		int[] dR = {0, 1, 0, -1};
		int[] dC = {1, 0, -1, 0};
		
		for(int i = 0; i < row; i++) {
			input = br.readLine().trim();
			
			map[i] = input.toCharArray();
		}
		
		// 풀이
		int nextR, nextC;
		
		for(int r = 0; r < row; r++) {
			for(int c = 0; c < col; c++) {
				if(map[r][c] == 'W') {
					for(int i = 0; i < 4; i++) {
						nextR = r + dR[i];
						nextC = c + dC[i];
						
						if(nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
							continue;
						}
						
						// 늑대 주변에 양 있으면 바로 종료
						if(map[nextR][nextC] == 'S') {
							System.out.println(0);
							return;
						} else if (map[nextR][nextC] == '.') {
							map[nextR][nextC] = 'D';
						}
					}
				}
			}
		}
		
		sb.append("1\n");
		for(int r = 0; r < row; r++) {
			for(int c = 0; c < col; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
