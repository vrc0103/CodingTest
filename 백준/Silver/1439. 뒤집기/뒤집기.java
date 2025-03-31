import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int res = 0;
        char[] input = br.readLine().trim().toCharArray();

        for(int i = 1; i < input.length; i++) {
            if(input[i] != input[0]) {
                res++;
                while(i < input.length && input[i] != input[0]) {
                    i++;
                }
            }
        }

        System.out.println(res);
    }
}