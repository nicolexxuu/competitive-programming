// 

import java.util.*;
import java.io.*;

public class farmupdates {
	static int[] res;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		boolean[] active = new boolean[N];
		Arrays.fill(active, true);
		ArrayList<int[]> edges = new ArrayList<>();
		adj = new ArrayList[N];
		for(int i = 0; i < N; i++) adj[i] = new ArrayList<>();
		
		int Q = Integer.parseInt(st.nextToken());
		int[][] queries = new int[Q][];
		boolean[] remove = new boolean[Q];
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			char t = st.nextToken().charAt(0);
			
			if(t == 'A') {
				edges.add(new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1});
				queries[q] = new int[0];
			} else if(t == 'D') {
				int x = Integer.parseInt(st.nextToken())-1;
				active[x] = false;
				queries[q] = new int[] {x};
			} else if(t == 'R') {
				int e = Integer.parseInt(st.nextToken())-1;
				remove[e] = true;
				queries[q] = new int[] {edges.get(e)[0], edges.get(e)[1]};
			}
		}
		
		for(int i = 0; i < edges.size(); i++) {
			if(!remove[i]) {
				adj[edges.get(i)[0]].add(edges.get(i)[1]);
				adj[edges.get(i)[1]].add(edges.get(i)[0]);
			}
		}
		
		res = new int[N];
		Arrays.fill(res, -1);
		for(int i = 0; i < N; i++) if(active[i]) dfs(i, Q);
		br.close();
		
		for(int z = Q-1; z >= 0; z--) {
			int[] q = queries[z];
			
			if(q.length == 1) dfs(q[0], z);
			
			if(q.length == 2) {
				adj[q[1]].add(q[0]);
				adj[q[0]].add(q[1]);
				if(res[q[0]] != -1) dfs(q[1], z);
				if(res[q[1]] != -1) dfs(q[0], z);
			}
		}
		
		StringBuilder out = new StringBuilder();
		for(int i : res) out.append(i).append('\n');
		System.out.print(out);
	}
	
	public static void dfs(int i, int val) {
		if(res[i] != -1) return;
		res[i] = val;
		for(int to : adj[i]) 
			dfs(to, val);
	}
}