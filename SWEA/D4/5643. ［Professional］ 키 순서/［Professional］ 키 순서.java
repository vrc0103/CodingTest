import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int res;
	static int num, cnt;
	static boolean[][] sml;

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
		int n1, n2;

		res = 0;
		
		num = Integer.parseInt(br.readLine().trim());
		cnt = Integer.parseInt(br.readLine().trim());
		
		sml = new boolean[num + 1][num + 1];
		
		while(cnt-- > 0) {
			// n1�� n2���� ����
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
			
			// ���� �л� Ž��
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
			
			// ū �л� Ž��
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
