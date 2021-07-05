import java.util.*;
import java.io.*;

public class GridPaths {
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		boolean[][] trap = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 0; j < n; j++) {
				if(s.charAt(j) == '*') trap[i][j] = true;
			}
		}
		
		br.close();
		
		int[][] dp = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(trap[i][j]) continue;
				if(i == 0 && j == 0) {
					dp[i][j] = 1;
					continue;
				}
				
				if(i > 0) dp[i][j] += dp[i-1][j];
				dp[i][j] %= MOD;
				if(j > 0) dp[i][j] += dp[i][j-1];
				dp[i][j] %= MOD;
			}
		}
		
		System.out.println(dp[n-1][n-1]);
	}
}