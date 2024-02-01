import java.util.*;
import java.io.*;

public class barnpainting {
	static int MOD = 1000000007;
	static ArrayList<Integer> adj[];
	static long dp[][];
	public static void main(String[] args) throws IOException {
		String file = "barnpainting";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		dp = new long[N][3];
		for(int i = 0; i < N; i++) adj[i] = new ArrayList<>();
		for(int i = 0; i < N-1; i++) { 
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1, y = Integer.parseInt(st.nextToken())-1;
			adj[x].add(y);
			adj[y].add(x);
		}
		
		for(long[] a : dp) Arrays.fill(a, 1);
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken())-1, c = Integer.parseInt(st.nextToken())-1;
			Arrays.fill(dp[b], 0);
			dp[b][c] = 1;
		}
		
		br.close();
		
		dfs(0, -1);
		System.out.println((dp[0][0] + dp[0][1] + dp[0][2]) % MOD);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		out.println((dp[0][0] + dp[0][1] + dp[0][2]) % MOD);
		out.close();
	}
	
	public static void dfs(int c, int p) {
		for(int to : adj[c]) {
			if(to == p) continue;
			dfs(to, c);
			long tot = (dp[to][0] + dp[to][1] + dp[to][2]); 
			for(int i = 0; i < 3; i++) 
				dp[c][i] = (dp[c][i] * (tot - dp[to][i]) % MOD) % MOD;
		}
	}
}