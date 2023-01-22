// Drought

import java.util.*;
import java.io.*;

public class drought {
	static int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] H = new int[N];
		st = new StringTokenizer(br.readLine());
		
		int min = Integer.MAX_VALUE, max = 0;
		for(int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, H[i]);
			min = Math.min(min, H[i]);
		}
		br.close();
		
		long res = 0;
		long[][] ps = new long[N][max+1];
		Arrays.fill(ps[0], 1);
		
		for(int start = 0; start <= (N%2 == 0 ? 0 : min); start++) {
			long[][] dp = new long[N][max+1];
			
			for(int i = 1; i < N; i++) {
				for(int end = 0; end <= Math.min(H[i-1], H[i]); end++)
					dp[i][end] = ps[i-1][H[i-1] - end];
				
				ps[i][0] = dp[i][0];
				for(int j = 1; j <= max; j++) {
					ps[i][j] = ps[i][j-1] + dp[i][j];
					if(ps[i][j] >= MOD) ps[i][j] -= MOD; // barely runs under time lol
				}
			}
			
			res += ps[N-1][max];
			if(res >= MOD) res -= MOD;
			for(int i = 0; i < N; i++) H[i]--;
		}
		
		System.out.println(res);
	}
}