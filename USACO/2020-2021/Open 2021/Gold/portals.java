// Portals

import java.util.*;
import java.io.*;

public class portals {
	static int N;
	static int[] sz, rt;

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(sz[a] < sz[b]) {
			sz[b] += sz[a];
			rt[a] = b;
		} else {
			sz[a] += sz[b];
			rt[b] = a;
		}
	}
	
	public static int find(int a) {
		while(rt[a] != a) {
			rt[a] = rt[rt[a]];
			a = rt[a];
		}
		
		return a;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		sz = new int[2*N];
		rt = new int[2*N];
		for(int i = 0; i < 2*N; i++) {
			sz[i] = 1;
			rt[i] = i;
		}
		
		ArrayList<Edge> edges = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int[] p = new int[4];
			for(int j = 0; j < 4; j++)
				p[j] = Integer.parseInt(st.nextToken())-1;
			edges.add(new Edge(p[0], p[1], 0));
			edges.add(new Edge(p[2], p[3], 0));
			edges.add(new Edge(p[0], p[2], c));
		}
		
		Collections.sort(edges, (a, b) -> a.c - b.c);
		br.close();
		
		int cost = 0;
		for(Edge e : edges) {
			if(find(e.a) != find(e.b)) {
				cost += e.c;
				union(e.a, e.b);
			}
		}
		
		System.out.println(cost);
	}
	
	static class Edge {
		int a, b, c;
		
		Edge(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}