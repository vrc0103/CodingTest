import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// 정보
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int res = 0;
		int num = Integer.parseInt(br.readLine().trim());
		ArrayList<int[]> checkPoint = new ArrayList<>();
		
		for(int n = 0; n < num; n++) {
			st = new StringTokenizer(br.readLine().trim());
			checkPoint.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			
			if(n > 0) {
				res += getDist(checkPoint.get(n), checkPoint.get(n - 1));
			}
		}
		
		// 풀이
		int diff;
		int maxDiff = 0;
		
		for(int i = 1; i < num - 1; i++) {
			
			diff = getDist(checkPoint.get(i - 1), checkPoint.get(i)) + getDist(checkPoint.get(i), checkPoint.get(i + 1)) - getDist(checkPoint.get(i - 1), checkPoint.get(i + 1));
			if(maxDiff < diff) {
				maxDiff = diff;
			}
		}
		
		res -= maxDiff;
		
		System.out.println(res);
	}
	
	static int getDist(int[] from, int[] to) {
		return Math.abs(to[0] - from[0]) + Math.abs(to[1] - from[1]);
	}
	
}
