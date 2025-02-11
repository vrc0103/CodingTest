import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int nInput, nRes;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nInput = Integer.parseInt(br.readLine());
        nRes = nInput - 543;

        System.out.println(nRes);
    }
}
