import java.io.*;

public class SWEA2058 {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();       //입력을 받아서
        char[] arr = s.toCharArray();   //배열로 쪼갬

        for(int i = 0; i < s.length(); i++) {
            sum += arr[i] - '0';
        }

        System.out.println(sum);
    }
}
