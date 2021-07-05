// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=669
// "Moocast", December 2016 Gold Division

import java.util.*;
import java.io.*;

public class moocast {
	public static void main(String[] args) throws IOException {
		String file = "moocast";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] x = new int[N];
		int[] y = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				edges.add(new Edge(i, j, ((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]))));
			}
		}
		
		Collections.sort(edges);
		
		int result = mstKruskal(edges, N);
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	static int[] parent;
	static int[] rank;
	// complexity: O (E log V)
	public static int mstKruskal(ArrayList<Edge> edges, int N) {
									//use either arraylist or linkedlist
									//if linkedlist: remove edges instead of indexing

		int maxWeight = 0;
		
		parent = new int[N];
		rank = new int[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		int clusters = N;
		for(int u = 0; u < edges.size(); u++) {
			int x = edges.get(u).a;
			int y = edges.get(u).b;
			if(union(x, y)) {
				maxWeight = edges.get(u).weight;
				clusters--;
				if(clusters == 1) break;
			}
		}
		
		return maxWeight;
	}
	
	// find representative of node k
	public static int find(int k) {
		if(parent[k] != k) {
			parent[k] = find(parent[k]); // try to make tree as shallow as possible
		}
		
		return parent[k];
	}
	
	// merge two sets containing "x" and "y"
	public static boolean union(int x, int y) {
		int xrep = find(x);
		int yrep = find(y);
		if(xrep == yrep) return false; //cycle formed!!
		if(rank[xrep] < rank[yrep]) {
			parent[xrep] = yrep;
		} else if (rank[yrep] < rank[xrep]) {
			parent[yrep] = xrep;
		} else {
			parent[xrep] = yrep;
			rank[yrep]++;
		}
		return true;
	}
	
	static class Edge implements Comparable<Edge> {
		
		int a, b; // two end points of an edge
		int weight;
		
		Edge(int a, int b, int weight){
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
		
		public int compareTo(Edge other) {
			return weight - other.weight;
		}
	}
}

