// Paired Up

import java.util.*;
import java.io.*;

public class pairedup {
	static int INF = 1000000005;
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] x = new int[N+1], y = new int[N+1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		x[N] = INF*2;
		br.close();
		
		ArrayList<int[]> chain = new ArrayList<>();
		long res = 0;
		for(int i = 0; i <= N; i++) {
			if(i > 0 && x[i] - x[i-1] > K) {
				if(T == 1) res += minSum(chain);
				else res += maxSum(chain);
				chain.clear();
			}
			
			chain.add(new int[] {x[i], y[i]});
		}
		
		System.out.println(res);
	}
	
	public static int minSum(ArrayList<int[]> chain) {
		if(chain.size() % 2 == 0) return 0;
		int min = INF;
		for(int i = 0; i < chain.size(); i++) {
			if(i % 2 == 0 || chain.get(i+1)[0] - chain.get(i-1)[0] <= K)
				min = Math.min(min, chain.get(i)[1]);
		}
		
		return min;
	}
	
	public static long maxSum(ArrayList<int[]> chain) {
		int n = chain.size();
		long[][] dp = new long[2][n+1]; // 0 - j is even, 1 - j is odd
		dp[0][n] = 0;
		dp[1][n] = -INF;
		int ub = n;
		
		for(int i = n-1; i >= 0; i--) {
			dp[0][i] = dp[0][i+1];
			dp[1][i] = dp[1][i+1];
			while(chain.get(ub-1)[0] - chain.get(i)[0] > K) ub--;
			if(i == 0 || i == n-1 || chain.get(i+1)[0] - chain.get(i-1)[0] <= K || (n - i) % 2 == 0)
				dp[0][i] = Math.max(dp[0][i], dp[1][ub] + chain.get(i)[1]);
			if(i == 0 || i == n-1 || chain.get(i+1)[0] - chain.get(i-1)[0] <= K || (n - i) % 2 == 1)
				dp[1][i] = Math.max(dp[1][i], dp[0][ub] + chain.get(i)[1]);
		}
		
		return n % 2 == 0 ? dp[0][0] : dp[1][0];
	}
}