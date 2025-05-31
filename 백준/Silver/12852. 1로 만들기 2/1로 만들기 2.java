import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.println(sb);
    }

    static void getRes() throws Exception {
        int num = Integer.parseInt(br.readLine().trim());
        int[] cnt = new int[num + 1];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] { num, 0 });
        cnt[num] = 0;

        while (true) {
            int[] now = q.poll();

            if (now[0] == 1) {
                break;
            }

            if (now[0] % 3 == 0 && cnt[now[0] / 3] == 0) {
                cnt[now[0] / 3] = now[1] + 1;
                q.add(new int[] { now[0] / 3, cnt[now[0] / 3] });

                if (now[0] / 3 == 1) {
                    break;
                }
            }
            if (now[0] % 2 == 0 && cnt[now[0] / 2] == 0) {
                cnt[now[0] / 2] = now[1] + 1;
                q.add(new int[] { now[0] / 2, cnt[now[0] / 2] });

                if (now[0] / 2 == 1) {
                    break;
                }
            }
            if (cnt[now[0] - 1] == 0) {
                cnt[now[0] - 1] = now[1] + 1;
                q.add(new int[] { now[0] - 1, cnt[now[0] - 1] });

                if (now[0] - 1 == 1) {
                    break;
                }
            }
        }

        int flow = 1;
        int count = cnt[1];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(1);

        while (flow < num) {
            if (flow * 3 <= num && cnt[flow * 3] == count - 1) {
                flow *= 3;
            } else if (flow * 2 <= num && cnt[flow * 2] == count - 1) {
                flow *= 2;
            } else if (flow + 1 <= num && cnt[flow + 1] == count - 1) {
                flow += 1;
            }

            stack.push(flow);
            count--;
        }

        sb.append(cnt[1]).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
    }
}
