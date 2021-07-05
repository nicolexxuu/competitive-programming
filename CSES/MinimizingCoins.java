import java.util.*;
import java.io.*;

public class MinimizingCoins {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int[] c = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) c[i] = Integer.parseInt(st.nextToken());
		br.close();
		
		int[] dp = new int[x+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for(int i = 1; i <= x; i++) {
			for(int j = 0; j < n; j++) {
				if(c[j] <= i && dp[i-c[j]] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], dp[i-c[j]] + 1);
			}
		}
		
		System.out.println(dp[x] != Integer.MAX_VALUE ? dp[x] : -1);
	}
}
