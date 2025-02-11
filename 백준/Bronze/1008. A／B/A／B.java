import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        double nA, nB;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        nA = Double.parseDouble(st.nextToken());
        nB = Double.parseDouble(st.nextToken());

        System.out.println(nA / nB);
    }
}