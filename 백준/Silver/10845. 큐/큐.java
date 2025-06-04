import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Node {
        Node prev, next;
        int num;

        Node(int num) {
            this.num = num;
        }
    }

    static class MyQueue {
        Node first, last;

        MyQueue() {
        }

        void push(int i) {
            Node tmp = new Node(i);

            if (last == null) {
                first = tmp;
            } else {
                last.next = tmp;
                tmp.prev = last;
            }

            last = tmp;
        }

        int pop() {
            if (first == null) {
                return -1;
            }

            int num = first.num;

            if (first.next == null) {
                first = null;
                last = null;
            } else {
                first.next.prev = null;
                first = first.next;
            }

            return num;
        }

        int size() {
            int len = 0;
            Node tmp = first;

            while (tmp != null) {
                len++;
                tmp = tmp.next;
            }

            return len;
        }

        int empty() {
            return size() > 0 ? 0 : 1;
        }

        int front() {
            return first != null ? first.num : -1;
        }

        int back() {
            return last != null ? last.num : -1;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        getRes();

        System.out.print(sb);
    }

    static void getRes() throws Exception {
        int cnt = Integer.parseInt(br.readLine().trim());
        MyQueue q = new MyQueue();

        while (cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());

            String cmd = st.nextToken();

            switch (cmd) {
                case "push":
                    q.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(q.pop()).append("\n");
                    break;
                case "size":
                    sb.append(q.size()).append("\n");
                    break;
                case "empty":
                    sb.append(q.empty()).append("\n");
                    break;
                case "front":
                    sb.append(q.front()).append("\n");
                    break;
                case "back":
                    sb.append(q.back()).append("\n");
                    break;
            }
        }
    }
}
