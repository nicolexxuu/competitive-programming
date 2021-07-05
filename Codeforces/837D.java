// D. Round Subset

import java.util.*;
import java.io.*;

public class D {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] factors = new int[n][2];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			long a = Long.parseLong(st.nextToken());
			
			for(long j = a; j % 2 == 0; j /= 2) factors[i][0]++;
			for(long j = a; j % 5 == 0; j /= 5) factors[i][1]++;
		}
		br.close();
		
		long[][] dp = new long[k+1][(26 * n) + 1];
		for(int j = 0; j <= k; j++) Arrays.fill(dp[j], -1);
		
		dp[0][0] = 0;
		for(int i = 1; i <= n; i++) { 
			int two = factors[i-1][0], five = factors[i-1][1];
			for(int j = Math.min(i, k); j > 0; j--) {
				
				for(int f = (26 * n); f >= five ; f--) { 
					if(dp[j-1][f-five] != -1)
					dp[j][f] = Math.max(dp[j][f], dp[j-1][f - five] + two);
				}
			}
		}
		
		long res = 0;
		for(int f = 0; f <= 26 * n; f++) res = Math.max(res, Math.min(f, dp[k][f]));
		System.out.println(res);
	}
}
