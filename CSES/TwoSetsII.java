import java.util.*;
import java.io.*;

public class TwoSetsII {
	static int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		solve(n);
		br.close();
	}

	public static void solve(int n) {
		if (n * (n + 1) / 2 % 2 != 0) {
			System.out.println(0);
			return;
		}
		int sum = n * (n + 1) / 4;
		int[][] dp = new int[sum + 1][n + 1];
		dp[0][0] = 1;
		
		for (int s = 0; s <= sum; s++) {
			for (int l = 1; l <= n-1; l++) {
				dp[s][l] = dp[s][l-1];
				if(s >= l) dp[s][l] = (dp[s][l] + dp[s - l][l - 1]) % MOD;
			}
		}
		
		System.out.println(dp[sum][n-1]);
	}
}