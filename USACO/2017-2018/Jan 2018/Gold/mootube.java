// MooTube

import java.util.*;
import java.io.*;

public class mootube {
	public static int[] rt = new int[100005], size = new int[100005];

	public static void main(String[] args) throws IOException {
		String file = "mootube";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		Edge[] edges = new Edge[N-1];
		Query[] queries = new Query[Q];
		int[] res = new int[Q];
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			queries[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		
		Arrays.sort(edges, (a, b) -> b.r - a.r);
		Arrays.sort(queries, (a, b) -> b.k - a.k);
		br.close();
		init();
		
		int toAdd = 0;
		for(Query q: queries) {
			while(toAdd < N-1 && edges[toAdd].r >= q.k) {
				merge(edges[toAdd].a, edges[toAdd].b); 
				toAdd++;
			}
			res[q.id] = size[root(q.v)] - 1;  
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(int i = 0; i < Q; i++) {
			System.out.println(res[i]);
			out.println(res[i]);
		}
		out.close();
	}
	
	public static void init() {
		for(int i = 0; i < 100005; i++) {
			rt[i] = i;
			size[i] = 1;
		}
	}
	
	public static int root(int a) {
		while(rt[a] != a) {
			rt[a] = rt[rt[a]];
			a = rt[a];
		}
		return a;
	}
	
	public static void merge(int a, int b) {
		int r_a = root(a);
		int r_b = root(b);
		rt[r_a] = r_b;
		size[r_b] += size[r_a];
	}
	
	public static class Edge{
		int a, b, r;
		Edge(int a, int b, int r) {
			this.a = a;
			this.b = b;
			this.r = r;
		}
	}
	
	public static class Query {
		int k, v, id;
		Query(int k, int v, int id) {
			this.k = k;
			this.v = v;
			this.id = id;
		}
	}
}
