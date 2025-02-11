import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int nA, nB;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        nA = Integer.parseInt(st.nextToken());
        nB = Integer.parseInt(st.nextToken());

        System.out.println(nA + nB);
    }
}