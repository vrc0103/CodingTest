import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int res = 0;
    static int size;
    static int apple, change;
    static char[][] map;
    static ArrayList<int[]> dirCh = new ArrayList<>();

    static int[] dR = {0, 1, 0, -1};
    static int[] dC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 정보
        size = Integer.parseInt(br.readLine().trim());
        map = new char[size + 1][size + 1];

        for(int i = 1; i <= size; i++) {
            Arrays.fill(map[i], '0');
        }

        apple = Integer.parseInt(br.readLine().trim());
        for(int i = 0; i < apple; i++) {
            st = new StringTokenizer(br.readLine().trim());

            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 'a';
        }

        change = Integer.parseInt(br.readLine().trim());
        for(int i = 0; i < change; i++) {
            st = new StringTokenizer(br.readLine().trim());

            dirCh.add(new int[] {Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)});
        }

        //풀이
        int dir = 0;
        int nextR, nextC;
        int[] head, tail;
        Deque<int[]> dq = new LinkedList<>();

        dq.add(new int[] {1, 1});

        head = dq.peekFirst();
        tail = dq.peekLast();

        map[1][1] = 's';

        while(true) {
            res++;

            nextR = head[0] + dR[dir];
            nextC = head[1] + dC[dir];

            // 벽이나 몸에 부딛힘
            if(nextR <= 0 || nextR > size || nextC <= 0 || nextC > size || map[nextR][nextC] == 's') {
                break;
            }

            dq.addFirst(new int[] {nextR, nextC});
            head = dq.peekFirst();

            // 이동 위치에 사과 없으면 꼬리 1 감소
            if(map[head[0]][head[1]] != 'a') {
                dq.removeLast();
                map[tail[0]][tail[1]] = '0';
                tail = dq.peekLast();
            }

            map[nextR][nextC] = 's';

            // 방향 전환
            if(dirCh.size() > 0 && dirCh.get(0)[0] == res) {
                if((char) dirCh.get(0)[1] == 'L') {
                    dir = (dir + 3) % 4;
                } else {
                    dir = (dir + 1) % 4;
                }

                dirCh.remove(0);
            }
        }

        System.out.println(res);
    }
}
