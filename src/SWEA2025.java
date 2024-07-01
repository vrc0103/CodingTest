import java.io.*;

public class SWEA2025 {
    public static void main(String[] args) throws IOException {
        int num;
        int sum = 0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        num = Integer.parseInt(s);

        for(int i = 1; i <= num; i++){
            sum += i;
        }

        System.out.println(sum);
    }
}
