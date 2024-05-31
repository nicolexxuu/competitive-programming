import java.util.*;
import java.io.*;

public class fertilizingpastures {
	static ArrayList<Integer>[] adj;
	static long[] A;
	static long[][] dp;
	static long[] sum, sz, dep;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		A = new long[N];
		sum = new long[N];
		sz = new long[N];
		dep = new long[N];
		dp = new long[N][2];
		for(int i = 0; i < N; i++) adj[i] = new ArrayList<>();
		
		for(int b = 1; b < N; b++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			A[b] = Long.parseLong(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		br.close();
		
		dfs(0, -1);
		if(T == 0) System.out.println((sz[0]-1)*2 + " " + dp[0][0]);
		else System.out.println((sz[0]-1)*2-dep[0] + " " + dp[0][1]);
	}
	
	public static void dfs(int curr, int par) {
		sum[curr] = A[curr];
		sz[curr] = 1;
		ArrayList<Integer> cs = new ArrayList<>();

		for(int to: adj[curr]) {
			if(to == par) continue;
			dfs(to, curr);
			sum[curr] += sum[to];
			sz[curr] += sz[to];
			dp[curr][0] += dp[to][0];
			dep[curr] = Math.max(dep[curr], dep[to]+1);
			cs.add(to);
		}
		
		Collections.sort(cs, (a, b) -> Long.compare(sum[b] * sz[a], sum[a] * sz[b]));
		long elapsed = 1;
		
		long[] ps = new long[cs.size()], save = new long[cs.size()];
		for(int i = 0; i < cs.size(); i++) {
			int to = cs.get(i);
			ps[i] = sum[to]; if(i > 0) ps[i] += ps[i-1];
			dp[curr][0] += sum[to] * elapsed;
			save[i] = sum[to] * elapsed;
			elapsed += sz[to] * 2;
		}
		
		if(cs.size() == 0) ;
		else {
			dp[curr][1] = Long.MAX_VALUE;
			for(int i = 0; i < cs.size(); i++) {
				int to = cs.get(i);
				if(dep[to] == dep[curr]-1) {
					long newTime = dp[curr][0];
					newTime -= dp[to][0] + save[i]; 
					newTime += dp[to][1] + sum[to] * (elapsed - sz[to] * 2);
					
					newTime -= (ps[cs.size()-1] - ps[i]) * sz[to] * 2;
					dp[curr][1] = Math.min(dp[curr][1], newTime);
				}
			}
		}
	}
}
