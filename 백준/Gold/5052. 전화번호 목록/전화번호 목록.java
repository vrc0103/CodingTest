import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static String res;
    static HashMap<Integer, ArrayList<String>> hmap;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("testCase.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            setInfo();

            getRes();

            sb.append(res);
        }

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        int cnt = Integer.parseInt(br.readLine().trim());

        hmap = new HashMap<>();

        for(int i = 1; i <= 10; i++) {
            hmap.put(i, new ArrayList<>());
        }
        
        while(cnt-- > 0) {
            String input = br.readLine().trim();

            hmap.get(input.length()).add(input);
        }
    }

    static void getRes() {
        res = "YES\n";

        // 길이가 2인 번호부터
        for(int len = 2; len <= 10; len++) {
            // 해당 길이의 각 번호
            for(String target : hmap.get(len)) {
                // 비교할 길이
                for(int i = 1; i < len; i++) {
                    // 해당 길이의 번호
                    for(String tmp : hmap.get(i)) {
                        //각 자릿수
                        for(int j = 0; j < i; j++) {
                            if(target.charAt(j) != tmp.charAt(j)) {
                                break;
                            }
                            // 모든 문자열이 포함됨
                            if(j == i - 1) {
                                res = "NO\n";

                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
