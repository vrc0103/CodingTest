import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int len;
    static int[] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInfo();

        getRes();

        System.out.print(sb);
    }

    static void setInfo() throws Exception {
        len = Integer.parseInt(br.readLine().trim());

        arr = new int[len];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            pq.offer(arr[i]);
        }
    }

    static void getRes() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(pq.poll());

        while(pq.size() > 0) {
            int num = pq.poll();

            if(list.get(list.size() - 1) != num) {
                list.add(num);
            }
        }

        for(int i = 0; i < len; i++) {
            int left = 0;
            int right = list.size() - 1;

            while(left <= right) {
                int mid = (left + right) / 2;

                if(arr[i] < list.get(mid)) {
                    right = mid - 1;
                } else if(arr[i] > list.get(mid)) {
                    left = mid + 1;
                } else {
                    sb.append(mid).append(" ");
                    break;
                }
            }
        }
        sb.append("\n");
    }
}
