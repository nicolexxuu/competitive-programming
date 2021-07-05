// Best Spot

import java.util.*;
import java.io.*;

public class bestspot {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		
		int[] favs = new int[F];
		for(int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			favs[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		for(int i = 0; i < P; i++) adj.add(new ArrayList<>());
		for(int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Edge(b, w));
			adj.get(b).add(new Edge(a, w));
		}
		br.close();

		double bestVal = Integer.MAX_VALUE;
		int bestPas = -1;
		
		for(int pas = 0; pas < P; pas++) {
			
			int[] dist = new int[P];
			Arrays.fill(dist, Integer.MAX_VALUE);
			boolean[] visited = new boolean[P];
			dijkstras(pas, adj, dist, visited);
			int sum = 0;
			for(int fav: favs) sum += dist[fav];
			double avg = (double)sum / F;
			if(avg < bestVal) {
				bestVal = avg;
				bestPas = pas;
			}
		}
		
		System.out.println(bestPas+1);
	}
	
	public static void dijkstras(int start, ArrayList<ArrayList<Edge>>adj, int[] dist, boolean[] visited) {
		dist[start] = 0;
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		toVisit.add(new Edge(start, 0));
		
		while(!toVisit.isEmpty()) {
			Edge curr = toVisit.remove();
			int id = curr.id, d = curr.weight;
			
			if(visited[id]) continue;
			visited[id] = true;
			
			for(Edge e: adj.get(id)) {
				if(d + e.weight < dist[e.id]) {
					dist[e.id] = d + e.weight;
					toVisit.add(new Edge(e.id, dist[e.id]));
				}
			}
		}
	}
	
	public static class Edge implements Comparable <Edge> {
		int id, weight;
		Edge(int id, int weight){
			this.id = id;
			this.weight = weight;
		}
		
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
}
