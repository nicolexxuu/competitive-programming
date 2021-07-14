// Dijkstra's Algorithm Implementation
// O(E log V) Approach

import java.util.*;
import java.io.*;

public class Dijkstras {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Edge(b, w));
			adj.get(b).add(new Edge(a, w));
		}
		br.close();
		
		
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		toVisit.add(new Edge(0, 0));
		boolean[] mark = new boolean[N];
		
		while(!toVisit.isEmpty()) {
			Edge curr = toVisit.remove();
			int id = curr.to;
			
			if(mark[id]) continue;
			mark[id] = true;
			
			for(Edge e: adj.get(id)) {
				if(e.weight + dist[id] < dist[e.to]) {
					dist[e.to] = e.weight + dist[id];
					toVisit.add(new Edge(e.to, dist[e.to]));
				}
			}
		}
	}
	
	public static class Edge implements Comparable<Edge> {
		int to, weight;
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge other) {
			return weight - other.weight;
		}
	}
}
