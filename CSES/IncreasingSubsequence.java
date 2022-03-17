// 1145 - Increasing Subsequence

import java.util.*;
import java.io.*;

public class IncreasingSubsequence {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			int l = 1, r = n;
			while(l < r) { // find first m such that x <= dp[m]
				int m = (l+r)/2;
				if(dp[m] >= x) r = m;
				else l = m+1;
			}
			
			dp[r] = x;
		}
		br.close();
		
		for(int i = n; i > 0; i--) {
			if(dp[i] < Integer.MAX_VALUE) {
				System.out.println(i);
				break;
			}
		}
	}
}