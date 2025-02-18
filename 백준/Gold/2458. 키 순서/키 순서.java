import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int res;
	static int num, cnt;
	static boolean[][] sml;

	public static void main(String[] args) throws Exception {
		setInfo();
		
		getRes();

		System.out.println(res);
	}

	static void setInfo() throws Exception {
		int n1, n2;
		
		st = new StringTokenizer(br.readLine().trim());
		num = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		
		sml = new boolean[num + 1][num + 1];
		
		while(cnt-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			
			sml[n1][n2] = true;
		}
	}
	
	static void getRes() {
		int now;
		int check;
		boolean[] checked;
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i <= num; i++) {
			check = 1;
			checked = new boolean[num + 1];
			checked[i] = true;
			
			queue.add(i);
			
			while(!queue.isEmpty()) {
				now = queue.remove();
				
				for(int j = 1; j <= num; j++) {
					if(sml[now][j] && !checked[j]) {
						queue.add(j);
						checked[j] = true;
						check++;
					}
				}
			}
			
			queue.add(i);
			
			while(!queue.isEmpty()) {
				now = queue.remove();
				
				for(int j = 1; j <= num; j++) {
					if(sml[j][now] && !checked[j]) {
						queue.add(j);
						checked[j] = true;
						check++;
					}
				}
			}
			
			if(check == num) {
				res++;
			}
		}
	}
}
