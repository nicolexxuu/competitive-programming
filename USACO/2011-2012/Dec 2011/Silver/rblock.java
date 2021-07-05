// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=98
// "Roadblock", 2011 December Silver Division

import java.util.*;
import java.io.*;

public class rblock {
	public static void main(String[] args) throws IOException {
		String file = "rblock";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		int[][] edges = new int[N][N];
		for(int i = 0; i < N; i++) {
			adj.add(new ArrayList<Edge>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int L = Integer.parseInt(st.nextToken());
			adj.get(A).add(new Edge(B, L));
			edges[A][B] = adj.get(A).size() - 1;
			adj.get(B).add(new Edge(A, L));
			edges[B][A] = adj.get(B).size() - 1;
		}
		br.close();
		
		int[] parent = new int[N];
		int[] dist = new int[N];
		dijkstra(adj, 0, dist, parent);
		int orig = dist[N-1];
		int max = dist[N-1];
		
		int from = N-1;
		int to = parent[N-1];
		while(to != -1) {
			int fromI = edges[from][to];
			int toI = edges[to][from];
			
			adj.get(from).get(fromI).weight *= 2;
			adj.get(to).get(toI).weight *= 2; 
			
			Arrays.fill(dist, Integer.MAX_VALUE);
			dijkstra(adj, 0, dist, new int[N]);
			
			max = Math.max(max, dist[N-1]);
			adj.get(from).get(fromI).weight /= 2;
			adj.get(to).get(toI).weight /= 2; 
			
			from = to;
			to = parent[to];
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(max - orig);
		out.println(max - orig);
		out.close();
	}
	
	public static void dijkstra(ArrayList<ArrayList<Edge>> adj, int src, int[] dist, int[] parent) {
		
		int N = adj.size();   // dist.length
		// s is between [0, N-1]
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		parent[0] = -1;
		
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
					parent[j] = i;
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
