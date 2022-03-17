// Flight Discount

import java.util.*;
import java.io.*;

public class FlightDiscount {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int i = 0; i < n; i++) adj.add(new ArrayList<>());
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			adj.get(Integer.parseInt(st.nextToken())-1).add(new Edge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		br.close();
		
		long[][] distance = new long[2][n];
		Arrays.fill(distance[0], Long.MAX_VALUE);
		Arrays.fill(distance[1], Long.MAX_VALUE);
		
		PriorityQueue<long[]> toVisit = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
		toVisit.add(new long[] {0, 0, 0});
		distance[0][0] = 0;
		while(!toVisit.isEmpty()) {
			long[] curr = toVisit.poll();
			int node = (int)curr[0];
			long dist = curr[1];
			int d = (int)curr[2];
			if(distance[d][node] != dist) continue;
			
			if(d == 1) {
				for(Edge e : adj.get(node)) {
					if(dist + e.cost < distance[1][e.to]) {
						distance[1][e.to] = dist + e.cost;
						toVisit.add(new long[] {e.to, distance[1][e.to], 1});
					}
				}
			} else {
				for(Edge e : adj.get(node)) {
					if(dist + e.cost < distance[0][e.to]) {
						distance[0][e.to]= dist + e.cost;
						toVisit.add(new long[] {e.to, distance[0][e.to], 0});
					}
					
					if(dist + e.cost/2 < distance[1][e.to]) {
						distance[1][e.to]= dist + e.cost/2;
						toVisit.add(new long[] {e.to, distance[1][e.to], 1});
					}
				}
			}
		}
		System.out.println(distance[1][n-1]);
	}
	
	public static class Edge implements Comparable<Edge> {
		int to;
		long cost;
		
		Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}
		
		public int compareTo(Edge other) {
			return Long.compare(cost, other.cost);
		}
	}
}