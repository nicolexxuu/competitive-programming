// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/index.php?page=viewproblem2&cpid=992
// "Wormhole Sort", 2020 January Silver Division

import java.util.*;
import java.io.*;

public class wormsort {
	public static void main(String[] args) throws IOException {
		String file = "wormsort";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[N];
		
		st = new StringTokenizer(br.readLine());
		boolean done = true;
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken()) - 1;
			if(n != i) done = false;
			nodes[i] = new Node(n);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			nodes[a].adj.add(new Edge(b, w));
			nodes[b].adj.add(new Edge(a, w));
		}
		
		br.close();
		
		if(done) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
			System.out.println(-1);
			out.println(-1);
			out.close();
		} else {
			int lo = 0;
			int hi = 1000000000;
			
			while(lo < hi) {
				int mid = (lo+hi+1)/2;
				for(Node n: nodes) n.cluster = -1;
				//graph traversal
				
				int c = 0;
				for(int i = 0; i < N; i++) {
					if(nodes[i].cluster != -1) continue;
					Deque<Integer> toVisit = new ArrayDeque<>();
					nodes[i].cluster = c;
					toVisit.add(i);
							
					while(!toVisit.isEmpty()) {
						int curr = toVisit.remove();
						
						for(Edge e: nodes[curr].adj) {
							if(e.weight >= mid && nodes[e.id].cluster == -1) {
								nodes[e.id].cluster = c;
								toVisit.add(e.id);
							}
						}
					}
					c++;
				}
				
				
				done = true;
				for(int i = 0; i < N; i++) {
					if(nodes[i].cluster != nodes[nodes[i].loc].cluster) {
						done = false;
						break;
					}
				}
				if(done) lo = mid;
				else hi = mid - 1;
			}
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
			System.out.println(hi);
			out.println(hi);
			out.close();
		}
	}
	
	public static class Node {
		int cluster;
		int loc;
		ArrayList<Edge> adj;
		Node(int loc){ 
			this.loc = loc;
			cluster = -1;
			adj = new ArrayList<>();
		}
	}
	
	public static class Edge {
		int id;
		int weight;
		Edge(int id, int weight){
			this.id = id;
			this.weight = weight;
		}
	}
}
