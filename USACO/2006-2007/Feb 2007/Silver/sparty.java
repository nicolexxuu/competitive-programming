// "Silver Cow Party"

import java.util.*;
import java.io.*;

public class sparty {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
		ArrayList<ArrayList<Pair>> adjR = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
			adjR.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Pair(b, t));
			adjR.get(b).add(new Pair(a, t));
		}
		
		br.close();
		
		int result = 0;
		int[] dist1 = new int[N], dist2 = new int[N];
		Arrays.fill(dist1, 2000000000);
		Arrays.fill(dist2, 2000000000);
		
		dijkstra(adj, dist1, X);
		dijkstra(adjR, dist2, X);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		
		for(int i = 0; i < N; i++) result = Math.max(result,  dist1[i] + dist2[i]);
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	
	public static void dijkstra(ArrayList<ArrayList<Pair>> adj, int[] dist, int X) {
		int N = dist.length;
		boolean[] mark = new boolean[N];
		PriorityQueue<Pair> toVisit = new PriorityQueue<>();
		toVisit.add(new Pair(X-1, 0));
		
		while(!toVisit.isEmpty()) {
			Pair curr = toVisit.remove();
			int id = curr.to, d = curr.weight;
			if(mark[id]) continue;
			mark[id] = true;
			
			for(Pair p: adj.get(id)) {
				int to = p.to, weight = p.weight;
				if(!mark[to] && d + weight < dist[to]) {
					dist[to] = weight + d;
					toVisit.add(new Pair(to, weight + d));
				}
			}
		}
		
	}
	
	public static class Pair implements Comparable<Pair>{
		int to, weight;
		
		Pair(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Pair other) {
			return weight - other.weight;
		}
	}
	
}
