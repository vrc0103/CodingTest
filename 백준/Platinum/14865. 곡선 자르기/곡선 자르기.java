import java.io.*;
import java.util.*;

/*
 * 가장 바깥쪽 봉우리 개수와
 * 가장 안쪽 봉우리 개수 출력
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int res1, res2;
    static ArrayList<MNT> arr = new ArrayList<>();

    static class MNT implements Comparable<MNT> {
        int left;
        int right;
        boolean first;
        boolean last;

        public MNT(int left, int right) {
            this.left = left;
            this.right = right;
            this.first = true;
            this.last = true;
        }

        @Override
        public int compareTo(MNT o) {
            // 왼쪽 봉우리부터
            return this.left - o.left;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cnt = Integer.parseInt(br.readLine().trim());

        cnt--;
        st = new StringTokenizer(br.readLine().trim());
        int firstX = Integer.parseInt(st.nextToken());
        int firstY = Integer.parseInt(st.nextToken());

        int nowX = 0;
        int nowY = 0;
        int pastX = firstX;
        int pastY = firstY;
        int mntL = 0;
        int mntR = 0;
        boolean up = false;
        int savedR = -1_000_000_000;

        while (cnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            nowX = Integer.parseInt(st.nextToken());
            nowY = Integer.parseInt(st.nextToken());

            // 봉우리 시작
            if (pastY < 0 && nowY > 0) {
                mntL = pastX;
                up = true;
            }
            // 봉우리 끝
            else if (pastY > 0 && nowY < 0) {
                mntR = nowX;

                if (up) {
                    arr.add(new MNT(mntL, mntR));
                    up = false;
                } else {
                    savedR = mntR;
                }

            }

            pastX = nowX;
            pastY = nowY;
        }

        // 시작, 끝 이어주기
        if(nowY < 0 && firstY > 0) {
            mntL = nowX;
            up = true;
        }
        else if(nowY > 0 && firstY < 0) {
            arr.add(new MNT(mntL, nowX));
        }

        // 다 끝났는데 올라간 선분이 있으면
        if (up) {
            arr.add(new MNT(mntL, savedR));
        }

        Collections.sort(arr);
        int len = arr.size();

        for (int i = 0; i < len; i++) {
            MNT now = arr.get(i);

            for (int j = i + 1; j < len; j++) {
                MNT next = arr.get(j);
                if (next.right < now.right) {
                    // now 안에 다른 봉우리 있음
                    now.last = false;
                    // next 밖에 다른 봉우리 있음
                    next.first = false;
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            MNT now = arr.get(i);
            if (now.first) {
                res1++;
            }

            if (now.last) {
                res2++;
            }
        }

        sb.append(res1).append(" ").append(res2).append("\n");

        System.out.print(sb);
    }

}
