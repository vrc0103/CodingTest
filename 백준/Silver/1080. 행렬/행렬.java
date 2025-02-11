import java.io.*;
import java.util.*;

public class Main {
	static int[] dR = {0, 0, 0, 1, 1, 1, 2, 2, 2};
	static int[] dC = {0, 1, 2, 0, 1, 2, 0, 1, 2};

	public static void main(String[] args) throws Exception {
		// 정보
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		char[][] raw = new char[row][col];
		for(int i = 0; i < row; i++) {
			raw[i] = br.readLine().trim().toCharArray();
		}
		
		char[][] dest = new char[row][col];
		for(int i = 0; i < row; i++) {
			dest[i] = br.readLine().trim().toCharArray();
		}
		
		// 풀이
		int nextR, nextC;
		int count = 0;
		
		for(int r = 0; r < row - 2; r++) {
			for(int c = 0; c < col - 2; c++) {
				// 값이 다르면 변환
				if(raw[r][c] != dest[r][c]) {
					count++;
					
					for(int i = 0; i < 9; i++) {
						nextR = r + dR[i];
						nextC = c + dC[i];
						
						raw[nextR][nextC] = raw[nextR][nextC] == '1' ? '0' : '1';
					}
				}
			}
		}
		
//		for(char[] tmp : raw) {
//			System.out.println(Arrays.toString(tmp));
//		}
		
		boolean eq = true;
		
		for(int i = 0; i < row; i++) {
			if(!Arrays.equals(raw[i], dest[i])) {
				eq = false;
				
				break;
			}
		}
		
		int res = eq ? count : -1;
		
		System.out.println(res);
	}

}
