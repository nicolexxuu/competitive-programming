// O (KM log N)

import java.util.*;
import java.io.*;

public class besslamotors {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		
		ArrayList<Edge>[] adj = new ArrayList[N];
		HashSet<Integer>[] mark = new HashSet[N];
		for(int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
			mark[i] = new HashSet<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1, v = Integer.parseInt(st.nextToken())-1, l = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, l, v));
			adj[v].add(new Edge(u, l, u));
		}
		br.close();

		int[] cnct = new int[N];

		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		for(int i = 0; i < C; i++) toVisit.add(new Edge(i, 0, i));
		
		while(!toVisit.isEmpty()) {
			int curr = toVisit.peek().to, src = toVisit.peek().src;
			long w = toVisit.peek().l;
			toVisit.remove();
			if(w > R || mark[curr].contains(src) || cnct[curr] >= K) continue;
			mark[curr].add(src);
			
			cnct[curr]++;
			for(Edge e : adj[curr]) {
				toVisit.add(new Edge(e.to, w + e.l, src));
			}
		}
		
		int cnt = 0;
		for(int i = C; i < N; i++) 
			if(cnct[i] >= K) cnt++;
		System.out.println(cnt);
		for(int i = C; i < N; i++) 
			if(cnct[i] >= K) System.out.println(i+1);
	}
	
	static class Edge implements Comparable<Edge>{
		int to, src;
		long l;
		
		Edge(int to, long l, int src) {
			this.to = to;
			this.l = l;
			this.src = src;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(l, o.l);
		}
	}
}