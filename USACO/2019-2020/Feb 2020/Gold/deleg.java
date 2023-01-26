import java.util.*;
import java.io.*;

public class deleg {
	static ArrayList<Integer>[] adj;
	static int N;
	public static void main(String[] args) throws IOException {
		String file = "deleg";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		int[] deg = new int[N+1];
		for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
			deg[a]++;
			deg[b]++;
		}
		adj[0].add(1);
		br.close();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		StringBuilder res = new StringBuilder();
		res.append(1);
		
		int star = 0;
		for(int i = 1; i <= N; i++) {
			if(deg[i] > 2) {
				if(star > 0) star = -1;
				else if(star != -1) star = i;
			}
		}
		
		int[] sz = new int[N+1];
		dfsStar(star, -1, sz);
			
		for(int k = 2; k <= N-1; k++) {
			if((N-1) % k != 0) {
				res.append(0);
				continue;
			}
			
			if(star != -1) {
				int[] cnt = new int[k+1];
				int rem = 0;
				for(int i : adj[star]) {
					int e = sz[i] % k;
					if(e != 0) {
						if(cnt[k-e] > 0) {
							cnt[k-e]--; rem--;
						} else {
							cnt[e]++; rem++; 
						}
					}
				}
				if(rem == 0) res.append(1);
				else res.append(0);
			} else {
				int[] dp = new int[N+1];
				boolean t = dfs(0, -1, k, dp);
				if(t && dp[0] == 1) res.append(1);
				else res.append(0);
			}
		}

		System.out.println(res);
		out.println(res);
		out.close();
	}
	
	public static boolean dfs(int idx, int par, int k, int[] dp) {
		HashMap<Integer, Integer> cnt = new HashMap<>();
		int rem = 0;
		for(int to : adj[idx]) {
			if(to == par) continue;
			if(!dfs(to, idx, k, dp)) return false;
			int edges = dp[to]+1;

			if(edges != k) {
				Integer r = cnt.get(k-edges);
				if(r != null) {
					if(r == 1) cnt.remove(k-edges);
					else cnt.put(k-edges, r-1);
					rem--;
				} else {
					rem++;
					cnt.put(edges, cnt.getOrDefault(edges, 0)+1);
				}
				
			}
		}
		if(rem > 1) return false;
		if(rem != 0) dp[idx] = cnt.entrySet().iterator().next().getKey();
		return true;
	}
	
	public static void dfsStar(int idx, int par, int[] sz) {
		if(idx == -1) return;
		sz[idx] = 1;
		for(int to : adj[idx]) {
			if(to == par) continue;
			dfsStar(to, idx, sz);
			sz[idx] += sz[to];
		}
	}
}