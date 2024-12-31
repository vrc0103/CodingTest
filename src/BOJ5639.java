import java.io.*;
import java.util.*;

public class BOJ5639 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int MAX = 10000;
    static ArrayList<Integer> inputs = new ArrayList<>();
    static Node bst;

    public static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public void insert(int val) {
            // 현재 값이 더 큼 -> 왼쪽으로
            if (this.val > val) {
                if (this.left == null) {
                    Node left = new Node(val);
                    this.left = left;
                } else {
                    this.left.insert(val);
                }
            }
            // 현재 값이 더 작음 -> 오른쪽으로
            else {
                if (this.right == null) {
                    Node right = new Node(val);
                    this.right = right;
                } else {
                    this.right.insert(val);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setPreOrder();

        getPostOrder(bst);

        System.out.print(sb.toString());
    }

    static void setPreOrder() throws IOException {
        String input;

        /*
         * 입력을 다 받을때까지 반복
         * input != null : 파일에서 읽어올 때(백준 채점 시) 마지막 줄까지 입력받음
         * !input.isEmpty() : IDE에서 입력받을 때 마지막 이후 엔터 치면 끝남
         */
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            inputs.add(Integer.parseInt(input));
        }

        // BST 생성
        bst = new Node(inputs.get(0));
        for (int i = 1; i < inputs.size(); i++) {
            bst.insert(inputs.get(i));
        }
    }

    static void getPostOrder(Node node) {
        // PostOrder : 왼쪽 -> 오른쪽 -> 루트
        if (node.left != null) {
            getPostOrder(node.left);
        }

        if (node.right != null) {
            getPostOrder(node.right);
        }

        sb.append(node.val).append("\n");
    }
}
