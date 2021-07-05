// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://usaco.org/index.php?page=viewproblem2&cpid=1017
//"Timeline", 2020 February Gold Division

/*
 * use a topological sort to help program update
 * all values within one traversal of all edges.
 */

import java.util.*;
import java.io.*;

public class timeline {
	public static void main(String[] args) throws IOException {
		String file = "timeline";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] res = new int[N];
		for(int j = 0; j < N; j++) {
			res[j] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>();
		for(int i = 0; i < N; i++) {
			edges.add(new ArrayList<Edge>());
		}
		
		int[] inDegree = new int[N];
		
		for(int j = 0; j < C; j++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken());
			
			edges.get(a).add(new Edge(b, x));
			inDegree[b]++;
		}
		
		br.close();
		
		LinkedList<Integer> list = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			if(inDegree[i] == 0) list.add(i);
		}
		
		int id = 0;
		while(id < list.size()) {
			int curr = list.get(id);
			
			for(Edge e : edges.get(curr)) {
				int neighbor = e.id;
				inDegree[neighbor]--;
				if(inDegree[neighbor] == 0) list.add(neighbor);
			}
			
			id++;
		}
		
		while(!list.isEmpty()) {
			int curr = list.removeFirst();
			for(Edge e: edges.get(curr)) {
				int neighbor = e.id;
				res[neighbor] = Math.max(res[neighbor], res[curr] + e.weight);
			}
		}
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		
		for(int i = 0; i < N; i++) {
			//System.out.println(res[i]);
			out.println(res[i]);
		}
		
		out.close();
	}
	
	public static class Edge{
		int id, weight;
		Edge(int i, int w){
			id = i;
			weight = w;
		}
	}
}

