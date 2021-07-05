// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=861
// "Fine Dining", 2016 December Gold Division

import java.util.*;
import java.io.*;

public class dining {
	public static void main(String[] args) throws IOException {
		String file = "dining";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			adj.add(new ArrayList<Edge>());
		}
		
		int[][] haybales = new int[K][2];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(new Edge(b, w));
			adj.get(b).add(new Edge(a, w));
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(st.nextToken()) - 1;
			int tasty = Integer.parseInt(st.nextToken());
			
			haybales[i][0] = loc;
			haybales[i][1] = tasty;
		}
		br.close();

		
		int[] dist = new int[N];
		dijkstra(adj, N-1, dist);
		
		//now, calculate with haybales
		int[] dist2 = new int[N + 1];
		
		//force cows to pass through one haybale
		for(int i = 0; i < K; i++) {
			int loc = haybales[i][0];
			int taste = haybales[i][1];
			
			adj.get(N).add(new Edge(loc, dist[loc] - taste));
			adj.get(loc).add(new Edge(N, dist[loc] - taste));
		}
		
		dijkstra(adj, N, dist2);
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		
		for(int i = 0; i < N - 1; i++) {
			if(dist2[i] <= dist[i]) {
				out.println(1);
			} else {
				out.println(0);
			}
		}
		out.close();
	}
	
	//run dijkstra's algo
	public static void dijkstra(ArrayList<ArrayList<Edge>> adj, int src, int[] dist) {
		
		int N = adj.size();   // dist.length
		// s is between [0, N-1]
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		
		// Pair < distance, NodeID >
		PriorityQueue<Pair<Integer, Integer>> list = 
				new PriorityQueue<Pair<Integer, Integer>>(N, 
						(a, b) -> a.getKey() - b.getKey());
		
		int n = adj.size();    // number of nodes
		
		// whether the shortest distance from "src" to node j is finalized
		boolean[] finalized = new boolean[n];
		

		Pair<Integer, Integer> p0 = new Pair<Integer, Integer>(0, src);
		list.add(p0);   // add "src" to Q
		
		while(!list.isEmpty()) {

			// take the node with the minimum estimated distance from "src"
			Pair<Integer, Integer> p = list.poll();
			
			int i = p.getValue(); // the current node ID

			if( finalized[i] ) continue;
			
			// stop if all remaining nodes are unreachable from "src"
			if( dist[i]==Integer.MAX_VALUE ) break;
		
			finalized[i] = true;
		
			for(Edge e : adj.get(i)) {
				int j = e.id;
				int weight = e.weight;
				if( weight<Integer.MAX_VALUE && !finalized[j] && 
						dist[i] + weight < dist[j] ) {

					dist[j] = dist[i] + weight;
					list.add( new Pair<Integer, Integer>(dist[j], j));
				}
			}
		}	
		
	}
	
	static class Edge {
		int id;   // from "this" to "id"
		int weight;
		
		Edge(int a, int b) {
			id = a;
			weight = b;
		}
	}
					//generic container
	static class Pair<T1, T2>{
		T1 key;
		T2 value;
		Pair(T1 a, T2 b){
			key = a;
			value = b;
		}
		
		T1 getKey() { return key; }
		T2 getValue() { return value; }
	}
}

