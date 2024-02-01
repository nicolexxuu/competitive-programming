import java.util.*;
import java.io.*;

public class redistributing {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] pref = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken())-1;
				pref[i] += (1 << a);
				if(a == i) break;
			}
		}
		
		long[][] dp = new long[1 << N][N];
		long[] ans = new long[1 << N];
		ans[0] = 1;
		
		for(int i = 0; i < N; i++) dp[1 << i][i] = 1;
		for(int m = 0; m < N; m++) {
			for(int mask = 1 << m; mask < (1 << (m+1)); mask++) { // O(2^N)
				
				for(int last = 0; last <= m; last++) { // O(N)
					if((mask & (1 << last)) == 0) continue;
					
					// extend curr cycle
					for(int to = 0; to < m; to++) {
						if((mask & (1 << to)) != 0 || (pref[last] & (1 << to)) == 0) continue;
						dp[mask + (1 << to)][to] += dp[mask][last];
					}
					
					// finish current cycle
					if((pref[last] & (1 << m)) != 0)
							ans[mask] += dp[mask][last];
				}
				
				// start new cycle
				for(int n = m+1; n < N; n++) dp[mask + (1 << n)][n] += ans[mask];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(st.nextToken());
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int h = 0, g = 0;
			for(int i = 0; i < N; i++) 
				if(s.charAt(i) == 'H') h += (1 << i);
				else g += (1 << i);
			
			sb.append(ans[h] * ans[g]).append('\n');
		}
		System.out.print(sb);
		br.close();
		
		
	}
}