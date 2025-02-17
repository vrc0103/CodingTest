import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int res;
	static int[] nums;
	static String input;
	
	public static void main(String[] args) throws Exception {
		input = br.readLine().trim();
		nums = new int[10];
		
		for(int i = 0; i < input.length(); i++) {
			nums[input.charAt(i) - '0']++;
		}
		
		for(int i = 0; i < 10; i++) {
			if(i == 6 || i == 9) {
				res = Math.max(res, (nums[6] + nums[9] + 1) / 2);
			} else {
				res = Math.max(res, nums[i]);
			}
		}
		
		System.out.println(res);
	}
}
