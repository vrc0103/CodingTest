import java.io.*;
import java.util.*;

public class Main {
	static int numV, numE;
	static int start;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	
	static class Node implements Comparable<Node> {
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		// 정보
		int from, to, cost;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		numV = Integer.parseInt(st.nextToken());
		numE = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine().trim());
		
		for(int i = 0; i <= numV; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < numE; i++) {
			st = new StringTokenizer(br.readLine().trim());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to, cost));
		}
		
		//풀이
		int[] costs = new int[numV + 1];
		Node now;
		int nowLoc, nowCost;
		int nextLoc, nextCost;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		Arrays.fill(costs, Integer.MAX_VALUE);
		costs[start] = 0;
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			now = pq.remove();
			nowLoc = now.to;
			nowCost = now.cost;
			
			if(nowCost < costs[nowLoc]) {
				continue;
			}
			
			for(Node next : graph.get(nowLoc)) {
				nextLoc = next.to;
				nextCost = nowCost + next.cost;
				
				if(nextCost < costs[nextLoc]) {
					costs[nextLoc] = nextCost;
					pq.add(new Node(nextLoc, nextCost));
				}
			}
		}
		
		// 출력
		for(int i = 1; i <= numV; i++) {
			if(costs[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(costs[i]);
			}
		}
	}

}
