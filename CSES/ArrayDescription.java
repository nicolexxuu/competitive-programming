import java.util.*;
import java.io.*;

public class ArrayDescription {
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		int[] a = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) a[i] = Integer.parseInt(st.nextToken());
		
		br.close();
		
		int[][] dp = new int[n+1][m+2];
		if(a[1] == 0) for(int i = 1; i <= m; i++) dp[1][i] = 1;
		else dp[1][a[1]] = 1;
		
		for(int i = 2; i <= n; i++) {
			if(a[i] != 0) {
				dp[i][a[i]] += ((dp[i-1][a[i]] + dp[i-1][a[i]-1]) % MOD + dp[i-1][a[i]+1]) % MOD;
				dp[i][a[i]] %= MOD;
			} else {
				for(int j = 1; j <= m; j++) {
					dp[i][j] += ((dp[i-1][j] + dp[i-1][j-1]) % MOD + dp[i-1][j+1]) % MOD;
					dp[i][j] %= MOD;
				}
			}
		}
		
		int res = 0;
		if(a[n] == 0) for(int i: dp[n]) res = (res + i) % MOD;
		else res = dp[n][a[n]];
		System.out.println(res);
	}
}