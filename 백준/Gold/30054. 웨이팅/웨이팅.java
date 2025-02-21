import java.io.*;
import java.util.*;

public class Main {
    // 예약 시각보다 일찍 도착했는 지 체크
    static boolean[] reserved;
    
    // idx에 해당하는 시각으로 예약한 사람이 입장했는지 체크 : 미리 도착하여 입장한 경우에도 true 처리
    static boolean[] visited;
    
    // 예약 정보
    // guests[i][0] : 예약
    // guests[i][1] : 도착
    static int[][] info;
    
    // 각 예약 시각에 해당하는 사람의 실제 도착 시각을 저장하는 배열
    static int[] arrived;
    
    // 예약 건수
    static int N;

    /*
     * 시간은 t = 1부터 300,000까지 진행(최대 100,000명이므로 200,000 시각에 전부 몰리는 경우까지 처리)한다.
     * t <= 200,000인 경우, 예약 시간에 맞춰 도착한 사람을 먼저 입장시킨다.
     * 예약 우선 대상이 없으면, guests 배열(도착 시각 기준 오름차순 정렬)에서 아직 입장하지 않은 사람 중 가장 먼저 온 사람을 입장시킨다.
     */
    static int waiting() {
        // 도착 시각 기준 오름차순으로 정렬
        Arrays.sort(info, (o1, o2) -> o1[1] - o2[1]);
        
        int res = 0;    // 결과 : 최대 대기 시간
        int idx = 0;    // 배열 탐색을 위한 인덱스
        int cnt = 0;    // 입장 처리된 예약 건수

        // 시뮬레이션 시간: t = 1부터 300,000까지 진행
        for (int t = 1; t <= 300_000; t++) {
            // 모든 예약이 입장되면 종료
            if (cnt == N) break;
            
            // 예약이 가능한 시간
            if (t <= 200_000) {
                // t시에 예약한 사람이 있고, 아직 입장하지 않았다면 입장 처리
                if (reserved[t] && !visited[t]) {
                    visited[t] = true;

                    // 대기 시간 갱신
                    res = Math.max(res, t - arrived[t]);
                    cnt++;

                    // 한 시간에 한 명만 입장 가능하므로, 해당 시각은 탐색 종료
                    continue;
                }
            }
            
            // info[idx][0] : 도착 순서로 정렬된 배열 -> 가장 앞 사람 탐색
            // -> visited[info[idx][0]] : 해당 사람이 식당에 입장했는지
            while (visited[info[idx][0]]) {
                idx++;
            }
            
            // 현재 시각 t가 가장 앞 사람의 도착 시각보다 작음 -> 입장 X
            if (t < info[idx][1]) {
                continue;
            }
            
            // 입장 가능하면 입장 처리하고 대기 시간 갱신
            visited[info[idx][0]] = true;
            res = Math.max(res, t - info[idx][1]);
            cnt++;
        }
        return res;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine().trim());
        
        reserved = new boolean[200_001];  // 최대 200,000시간간
        visited = new boolean[200_001];   // 예약 입장 여부 체크
        info = new int[N][2];           // 모든 예약 정보를 저장
        arrived = new int[200_001];       // 각 예약 시각에 해당하는 도착 시각
        
        // 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int reserveTime = Integer.parseInt(st.nextToken());
            int arriveTime = Integer.parseInt(st.nextToken());
            info[i][0] = reserveTime;
            info[i][1] = arriveTime;
            // 해당 예약 시각에 예약한 사람의 도착 시각 저장
            arrived[reserveTime] = arriveTime;
            // 예약 시각보다 늦지 않게 도착했으면 reserved[t]를 true로 표시
            if (reserveTime >= arriveTime) {
                reserved[reserveTime] = true;
            }
        }
        
        // 시뮬레이션을 통해 최대 대기 시간을 구하고 출력
        System.out.println(waiting());
    }
}
