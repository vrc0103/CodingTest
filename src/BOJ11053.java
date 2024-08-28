import java.io.*;
import java.util.*;

public class BOJ11053 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res;
    static int len;

    static class Arr implements Comparable<Arr> {
        int key;
        int val;

        public Arr(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(Arr o) {
            // 내림차순
            return o.val - this.val;
        }
    }

    public static void main(String[] args) throws IOException {
        getMax();

        System.out.println(res);
    }

    static void getMax() throws IOException {
        int num;
        int size;
        int val;
        ArrayList<Arr> arr = new ArrayList<>();

        len = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        arr.add(new Arr(Integer.parseInt(st.nextToken()), 1));

        for (int cnt = 1; cnt < len; cnt++) {
            num = Integer.parseInt(st.nextToken());
            size = arr.size();
            val = 1;

            // 증가 배열 중 가장 큰 값 확인
            for (int j = 0; j < size; j++) {
                // 자신보다 작은 숫자이면 해당 값의 길이 +1
                if (arr.get(j).key < num) {
                    val = arr.get(j).val + 1;
                    break;
                }
            }

            arr.add(new Arr(num, val));

            Collections.sort(arr);
        }

        res = arr.get(0).val;
    }
}
