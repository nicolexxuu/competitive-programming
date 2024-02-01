// 

import java.util.*;
import java.io.*;

public class mooriokart {
	static long MOD = 1000000007;
	static boolean[] mark;
	static ArrayList<int[]>[] adj;
	public static void dfs1(int i, ArrayList<Integer> comp) {
		if(mark[i]) return;
		mark[i] = true;
		comp.add(i);
		for(int[] to : adj[i]) {
			dfs1(to[0], comp);
		}
	}
	
	public static void dfs2(int i, int par, int ori, int d, ArrayList<Integer> lens) {
		for(int[] to : adj[i]) {
			if(to[0] == par) continue;
			if(ori < to[0]) lens.add(d+to[1]);
			dfs2(to[0], i, ori, d+to[1], lens);
		}
	}
	
	public static void main(String[] args) throws IOException {
		String file = "mooriokart";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken());
		int K = N-M;
		ArrayList<long[]> clusters = new ArrayList<>();
		adj = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken()), D = Integer.parseInt(st.nextToken());
			adj[A].add(new int[] {B, D});
			adj[B].add(new int[] {A, D});
		}
		br.close();
		
		long[][][] dp = new long[Y+1][2][2]; // j=0: number of paths, j=1: sums of path sums
		dp[Math.min(K*X, Y)][0][0] = 1; 
		dp[Math.min(K*X, Y)][1][0] = K*X;
		mark = new boolean[N+1];
		
		int idx = 1;
		for(int i = 1; i <= N; i++) { 
			if(mark[i]) continue;
			ArrayList<Integer> comp = new ArrayList<>();
			dfs1(i, comp); 
			ArrayList<Integer> lens = new ArrayList<>();
			for(int j : comp) dfs2(j, -1, j, 0, lens);
			
			HashMap<Integer, Integer> g = new HashMap<>();
			for(int j : lens) g.put(j, g.getOrDefault(j, 0)+1);
			
			for(int prev = 0; prev <= Y; prev++) {
				for(int len : g.keySet()) {
					int c = g.get(len), d = len * c;
					long a = dp[prev][0][1-idx], b = dp[prev][1][1-idx];
					int newI = Math.min(prev+len, Y);
					dp[newI][0][idx] = (dp[newI][0][idx] + (a * c) % MOD) % MOD;
					dp[newI][1][idx] = (dp[newI][1][idx] + (a * d) % MOD + (b * c) % MOD) % MOD;
				}
			}
			
			idx = 1 - idx;
			for(int j = 0; j <= Y; j++) dp[j][0][idx] = dp[j][1][idx] = 0;
		}
		
		long ans = dp[Y][1][1-idx%2];
		for(int i = K-1; i >= 1; i--) {
			ans = (ans * i) % MOD;
			ans = (ans * 2) % MOD;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		out.println(ans);
		out.close();
	}
}