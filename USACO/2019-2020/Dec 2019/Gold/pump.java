// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/index.php?page=viewproblem2&cpid=969
// "Milk Pumping", 2019 December Gold Division

import java.util.*;
import java.io.*;

public class pump {
	public static void main(String[] args) throws IOException {
		String file = "pump";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int i=0; i<N; i++) {
			adj.add(new ArrayList<Edge>());
		}

		TreeSet<Integer> flows = new TreeSet<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			int flow = Integer.parseInt(st.nextToken());
			
			adj.get(from).add(new Edge(to, cost, flow));
			adj.get(to).add(new Edge(from, cost, flow));
			flows.add(flow);
		}
		
		br.close();
		
		double bestVal = 0;
		
		for(Integer minFlow: flows) {
			int cost = dijkstra(adj, 0, minFlow);
			double temp = (double)minFlow/cost;
			if(temp > bestVal) {
				bestVal = temp;
			}
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println((long)(bestVal * 1000000));
		out.println((long)(bestVal * 1000000));
		out.close();
	}
	
	// find the shortest distance thru positive edges from source "src" 
	// complexity: O( E log V)
	public static int dijkstra(ArrayList<ArrayList<Edge>> adj, int src, int minFlow) {
		
		int N = adj.size();   // dist.length
		// s is between [0, N-1]
		
		int[] dist = new int[N];
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
			if( dist[i]==Integer.MAX_VALUE ) return -1;
		
			finalized[i] = true;
			
			for(Edge e : adj.get(i)) {
				int j = e.id;
				int cost = e.cost;
				int flow = e.flow;
				if( cost<Integer.MAX_VALUE && !finalized[j] && 
						dist[i] + cost < dist[j] && flow >= minFlow) {

					dist[j] = dist[i] + cost;
					list.add( new Pair<Integer, Integer>(dist[j], j));
				}
			}
		}	
		
		if(dist[N-1] == Integer.MAX_VALUE) return -1;
		return dist[N-1];
	}
	
	static class Edge {
		int id;   // from "this" to "id"
		int cost;
		int flow;
		
		Edge(int a, int b, int c) {
			id = a;
			cost = b;
			flow = c;
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
