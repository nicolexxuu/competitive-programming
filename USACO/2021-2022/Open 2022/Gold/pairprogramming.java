// 

import java.util.*;
import java.io.*;

public class pairprogramming {
	static int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] p1 = new int[N+1], p2 = new int[N+1];
			Arrays.fill(p1, -1);
			Arrays.fill(p2, -1);
			
			st = new StringTokenizer(br.readLine());
			char[] b = (' ' + st.nextToken()).toCharArray();
			st = new StringTokenizer(br.readLine());
			char[] e = (' ' + st.nextToken()).toCharArray();
			
			for(int i = 1; i <= N; i++) {
				if(b[i] != '+') p1[i] = b[i] - '0';
				if(e[i] != '+') p2[i] = e[i] - '0';
			}
			
			long[][] dp = new long[N+1][N+1];
			for(int i = 0; i <= N; i++) dp[i][0] = dp[0][i] = 1;
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(p1[i] == 0 || p2[j] == 0) {
						if(p2[j] > 0) dp[i][j] = dp[i][j-1];
						else if(p1[i] > 0) dp[i][j] = dp[i-1][j];
						else dp[i][j] = 1;
						
						if(p1[i] == -1) dp[i][j] += dp[i-1][j];
						if(p2[j] == -1) dp[i][j] += dp[i][j-1];
					} else if(p1[i] > 0 && p2[j] > 0 || p1[i] == -1 && p2[j] == -1 || p1[i] == 1 || p2[j] == 1) { // same type of operation
						dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD + MOD - dp[i-1][j-1];
					} else { // mixed
						dp[i][j] = dp[i-1][j] + dp[i][j-1];
					}
					
					dp[i][j] %= MOD;
				}
			}
			
			System.out.println(dp[N][N]);
		}
		br.close();
		
	}
}
