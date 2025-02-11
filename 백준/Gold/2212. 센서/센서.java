import java.io.*;
import java.util.*;

public class Main {
	static int res;
	static int lab;
	static int sen;
	static int[] loc;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		// 정보
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		sen = Integer.parseInt(br.readLine().trim());
		lab = Integer.parseInt(br.readLine().trim());
		
		loc = new int[sen];
		st = new StringTokenizer(br.readLine().trim());
		for(int i = 0; i < sen; i++) {
			loc[i] = Integer.parseInt(st.nextToken());
		}
		
		// 풀이
		Arrays.sort(loc);
		
		int[] dist = new int[sen - 1];
		
		for(int i = 0; i < sen - 1; i++) {
			dist[i] = loc[i + 1] - loc[i];
		}
		
		Arrays.sort(dist);
		
		for(int i = 0; i < sen - lab; i++) {
			res += dist[i];
		}
		
		System.out.println(res);
	}

}
