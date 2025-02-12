import java.io.*;

public class Main {
	static String input1;
	static String input2;
	static int[][] lcs;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// 정보
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input1 = br.readLine().trim();
		input2 = br.readLine().trim();
		
		int len1 = input1.length();
		int len2 = input2.length();
		
		lcs = new int[input1.length() + 1][input2.length() + 1];
		
		// lcs 길이 찾기
		for(int i = 1; i <= len1; i++) {
			for(int j = 1; j <= len2; j++) {
				// 해당 위치의 문자가 동일하면 lcs 길이 1 증가
				if(input1.charAt(i - 1) == input2.charAt(j - 1)) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				}
				// 문자가 다르면 기존 배열에서 큰 값 선택 : input1에서 버릴 지 input2에서 버릴 지 선택
				else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}
		
		
		int i = len1;
		int j = len2;
		
		while(i > 0 && j > 0) {
			// 두 문자열에 모두 포함된 문자 : lcs에 해당
			if(input1.charAt(i - 1) == input2.charAt(j - 1)) {
				sb.append(input1.charAt(i - 1));
				i--;
				j--;
			}
			//아닌 경우
			else {
				// 더 높은 값인 칸으로 이동 : lcs가 더 길었던 경로 선택
				if (lcs[i - 1][j] >= lcs[i][j - 1]) {
		            i--; // 위쪽 셀로 이동
		        } else {
		            j--; // 왼쪽 셀로 이동
		        }
			}
		}
		
		System.out.println(lcs[len1][len2]);
		System.out.println(sb.reverse());
	}

}
