// Fruit Feast

import java.util.*;
import java.io.*;

public class feast2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		br.close();
		
		int[][] dp = new int[T+1][2];
		
		for(int full = 1; full <= T; full++) {
			dp[full][0] = dp[full-1][0];
			if(full >= A) dp[full][0] = Math.max(dp[full][0], dp[full-A][0] + A);
			if(full >= B) dp[full][0] = Math.max(dp[full][0], dp[full-B][0] + B);
		}
		
		
		for(int full = 1; full <= T; full++) {
			dp[full][1] = dp[full-1][1];
			if(full >= A) dp[full][1] = Math.max(dp[full][1], dp[full-A][1] + A);
			if(full >= B) dp[full][1] = Math.max(dp[full][1], dp[full-B][1] + B);
			if(full * 2 <= T) dp[full][1] = Math.max(dp[full][1], dp[full*2][0]/2);
			if(full * 2 + 1 <= T) dp[full][1] = Math.max(dp[full][1], dp[full*2+1][0]/2);
		}
		
		System.out.println(Math.max(dp[T][0], dp[T][1]));
	}
}
