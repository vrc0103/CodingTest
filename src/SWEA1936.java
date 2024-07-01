//가위1 , 바위2 , 보3

import java.util.*;
import java.io.*;

public class SWEA1936 {
    public static void main(String[] args) throws IOException {
        int A, B;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String sTmp = bf.readLine();
        StringTokenizer st = new StringTokenizer(sTmp, " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        if( (A == 1 && B == 2) || (A == 2 && B == 3) || (A == 3 && B == 1) )
            System.out.println("B");
        else
            System.out.println("A");
    }
}
