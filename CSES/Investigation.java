import java.util.*;
import java.io.*;

public class Investigation {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int i = 0; i < n; i++) adj.add(new ArrayList<>());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Edge(b, c));
		}
		br.close();
		
		boolean[] mark = new boolean[n];
		long[] dist = new long[n], numRoutes = new long[n];
		int[] minFlights = new int[n], maxFlights = new int[n];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[0] = 0;
		numRoutes[0] = 1;
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		toVisit.add(new Edge(0, 0));
		
		while(!toVisit.isEmpty()) {
			Edge curr = toVisit.remove();
			int id = curr.to;
			if(mark[id]) continue;
			mark[id] = true;
			
			for(Edge e : adj.get(id)) {
				if(dist[id] + e.c == dist[e.to]) {
					numRoutes[e.to] = (numRoutes[e.to] + numRoutes[id]) % 1000000007;
					minFlights[e.to] = Math.min(minFlights[e.to], minFlights[id] + 1); 
					maxFlights[e.to] = Math.max(maxFlights[e.to], maxFlights[id] + 1); 
				} else if(dist[id] + e.c < dist[e.to]) {
					dist[e.to] = dist[id] + e.c;
					numRoutes[e.to] = numRoutes[id]; 
					minFlights[e.to] = minFlights[id] + 1;
					maxFlights[e.to] = maxFlights[id] + 1; 
					toVisit.add(new Edge(e.to, dist[e.to]));
				}
			}
		}
		
		System.out.println(dist[n-1] + " " + numRoutes[n-1] + " " + minFlights[n-1] + " " + maxFlights[n-1]);
	}
	
	public static class Edge implements Comparable<Edge> {
		int to;
		long c;
		
		Edge(int to, long c) {
			this.to = to;
			this.c = c;
		}
		
		public int compareTo(Edge other) { return Long.compare(c, other.c); }
	}
}