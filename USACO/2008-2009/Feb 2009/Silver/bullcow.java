// Bulls and Cows

import java.util.*;
import java.io.*;

public class bullcow {
	static int MOD = 5000011;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		br.close();
		
		int[][] dp = new int[N][2]; //number of sequences that end in bull or cow
									  //1 - bull, 0 - cow
		dp[0][0] = dp[0][1] = 1;
		for(int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-1][1];
			dp[i][0] %= MOD;
			if(i > K) dp[i][1] = (dp[i-K-1][0] + dp[i-K-1][1]);
			else dp[i][1] = 1;
			dp[i][1] %= MOD;
		}

		int[] ans = new int[N];
		Arrays.fill(ans, -1);
		ans[0] = 2;
		System.out.println(solve(N-1, K, ans));
		//for(int i: ans) System.out.println(i);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//System.out.println((dp[N-1][0] + dp[N-1][1]) % MOD);
		//out.println(result);
		//out.close();
	}
	
	public static int solve(int N, int K, int[] ans) {
		if(N < 0) return 1;
		if(ans[N] != -1) return ans[N];
		
		ans[N] = (solve(N-1, K, ans) + solve(N-K-1, K, ans)) % 5000011;
		return ans[N];
	}
}
