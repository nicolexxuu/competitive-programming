// Shortcut

import java.util.*;
import java.io.*;

public class shortcut {
	public static void main(String[] args) throws IOException {
		String file = "shortcut";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[] cows = new int[N];
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Edge(b, t));
			adj.get(b).add(new Edge(a, t));
		}
		br.close();
		
		// run Dijkstra's; keep track of parents
		int[] dist = new int[N];
		int[] par = new int[N];
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
			par[id] = curr.from;
			
			for(Edge e: adj.get(id)) {
				if(e.len + dist[id] <= dist[e.to]) {
					dist[e.to] = e.len + dist[id];
					Edge n = new Edge(e.to, dist[e.to]);
					n.from = id;
					toVisit.add(n);
				}
			}
		}
		
		int[] total = new int[N];

		// O(N^2) approach: backtrack to count how many passed thru each node
//		for(int i = 1; i < N; i++) {
//			int p = i;
//			while(p != -1) {
//				total[p] += cows[i];
//				p = par[p];
//			}
//		}
		
		// O(N) approach: DFS on Dijkstra-generated tree
		ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			total[i] = cows[i];
			adj2.add(new ArrayList<>());
		}
		for(int i = 1; i < N; i++) adj2.get(par[i]).add(i);
		dfs(0, -1, total, adj2);
		
		
		long res = 0;
		for(int i = 1; i < N; i++) res = Math.max(res, (long)total[i] * (dist[i] - T));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		out.println(res);
		out.close();
	}
	
	public static void dfs(int node, int from, int[] total, ArrayList<ArrayList<Integer>> adj2) {
		for(int to : adj2.get(node)) {
			if(to == from) continue;
			dfs(to, node, total, adj2);
			total[node] += total[to];
		}
	}
	
	public static class Edge implements Comparable<Edge> {
		int to, len;
		int from = -1;
		Edge(int to, int len){
			this.to = to;
			this.len = len;
		}
		
		public int compareTo(Edge other) {
			if(len == other.len) return from - other.from;
			return len - other.len;
		}
	}
}
