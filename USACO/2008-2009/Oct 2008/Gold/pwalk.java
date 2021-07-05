// "Pasture Walking"

import java.util.*;
import java.io.*;

public class pwalk {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int i = 0; i < N; i++) adj.add(new ArrayList<Edge>());
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Edge(b, w));
			adj.get(b).add(new Edge(a, w));
		}
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			dfs(a, b, adj, new boolean[N], 0);
		}
		br.close();
	}
	
	public static void dfs(int curr, int target, ArrayList<ArrayList<Edge>> adj, boolean[] visited, int dist) {
		if(visited[curr]) return;
		visited[curr] = true;
		if(curr == target) {
			System.out.println(dist);
			return;
		}
		
		for(Edge e: adj.get(curr)) {
			dfs(e.to, target, adj, visited, dist+e.weight);
		}
	}
	
	public static class Edge {
		int to, weight;
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
}
