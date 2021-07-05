// Dueling GPS's

import java.util.*;
import java.io.*;

public class gpsduel {
	public static void main(String[] args) throws IOException {
		String file = "gpsduel";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int i = 0; i < N; i++) adj.add(new ArrayList<Edge>());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1, to = Integer.parseInt(st.nextToken())-1;
			int p = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());
			adj.get(to).add(new Edge(from, p, q));
		}
		br.close();
		
		int[][] dist = new int[N][2];
		for(int[] A: dist) Arrays.fill(A, 2000000000);
		
		dijkstra(adj, dist, 0, N);
		dijkstra(adj, dist, 1, N);

		
		for(int from = 0; from < N; from++) {
			for(Edge e: adj.get(from)) {
				int to = e.to, p = e.p, q = e.q;
				e.p = 0;
				if(dist[to][0] - dist[from][0] != p) e.p++; 
				if(dist[to][1] - dist[from][1] != q) e.p++; 
			}
		}
		
		for(int[] A: dist) Arrays.fill(A, 2000000000);
		dijkstra(adj, dist, 0, N);
		int result = dist[0][0];
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class Edge{ 
		int to, p, q;
		Edge (int to, int p, int q) {
			this.to = to;
			this.p = p;
			this.q = q;
		}
	}
	
	public static void dijkstra (ArrayList<ArrayList<Edge>> adj, int[][] dist, int gps, int N) {
		boolean[] mark = new boolean[N];
		
		PriorityQueue<Edge> toVisit;
		if(gps == 0) toVisit = new PriorityQueue<>((a, b) -> a.p - b.p);
		else toVisit = new PriorityQueue<>((a, b) -> a.q - b.q);
		
		toVisit.add(new Edge(N-1, 0, 0));
		
		while (!toVisit.isEmpty()) {
			Edge curr = toVisit.remove();
			if(mark[curr.to]) continue;
			
			int id = curr.to, weight;
			if(gps == 0) weight = curr.p;
			else weight = curr.q;
			
			mark[id] = true; 
			dist[id][gps] = weight;
			
			for(Edge e: adj.get(id)) {
				if(mark[e.to]) continue;
				if(gps == 0 && weight + e.p < dist[e.to][gps]) toVisit.add(new Edge(e.to, weight + e.p, 0));
				else if (gps == 1 && weight + e.q < dist[e.to][gps]) toVisit.add(new Edge(e.to, 0, weight + e.q));
			}
			
		}
	}
}
