import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        search();

        System.out.print(sb);
    }

    static void search() throws IOException {
        int len, count, num;
        int[] arr;

        len = Integer.parseInt(br.readLine().trim());
        arr = new int[len];
        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        count = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < count; i++) {
            num = Integer.parseInt(st.nextToken());

            sb.append(binarySearch(num, arr)).append("\n");
        }
    }

    static int binarySearch(int num, int[] arr) {
        int idx = arr.length / 2;
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            idx = (min + max) / 2;

            if (num > arr[idx]) {
                min = idx + 1;
            } else if (num < arr[idx]) {
                max = idx - 1;
            } else {
                return 1;
            }
        }

        return 0;
    }
}
