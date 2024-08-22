import java.io.*;
import java.util.*;

public class BOJ1406 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        getStr();

        System.out.print(sb.toString());
    }

    static void getStr() throws IOException {
        // 커서 변수를 이용한 링크드 리스트 접근은 매 탐색마다 처음부터 탐색을 시작함
        // -> 이터레이터와 시간복잡도가 다름
        // int cursor;
        int num, len;
        String start;
        String cmd;
        LinkedList<Character> str = new LinkedList<>();

        // https://www.tcpschool.com/java/java_collectionFramework_iterator
        // https://brandpark.github.io/java/2021/01/24/iterator.html
        // Iterator : Collection 인터페이스에 모두 사용, next만 가능
        // ListIterator : List 인터페이스만 사용, 양방향 이동(next, previous) 및 시작 위치 지정 가능
        ListIterator<Character> cursor;

        // 초기 문자열
        start = br.readLine().trim();
        len = start.length();
        for (int i = 0; i < len; i++) {
            str.add(start.charAt(i));
        }

        cursor = str.listIterator(len);

        // 명령 수행
        num = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine().trim());
            cmd = st.nextToken();

            if (cmd.equals("L")) {
                if (cursor.hasPrevious()) {
                    cursor.previous();
                }
            } else if (cmd.equals("D")) {
                if (cursor.hasNext()) {
                    cursor.next();
                }
            } else if (cmd.equals("B")) {
                // 커서 왼쪽의 문자 삭제
                // remove는 가장 최근 반환된 요소를 제거함
                // -> previous로 커서를 이동시켜 반환된 값(커서 이동 전 커서의 앞에 있던 값)을 삭제함
                if (cursor.hasPrevious()) {
                    cursor.previous();
                    cursor.remove();
                }
            } else if (cmd.equals("P")) {
                cursor.add(st.nextToken().charAt(0));
            }
        }

        for (char ch : str) {
            sb.append(ch);
        }
        sb.append("\n");
    }
}
