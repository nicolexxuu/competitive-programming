// Greedy Pie Eaters

import java.util.*;
import java.io.*;

public class pieaters {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] w = new int[M], l = new int[M], r = new int[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			l[i] = Integer.parseInt(st.nextToken()) - 1;
			r[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		int[][] dp = new int[N][N];
		int[][][] best = new int[N][N][N];
				
		br.close();
		
		for(int i = 0; i < M; i++) {
			for(int j = l[i]; j <= r[i]; j++) {
				best[j][l[i]][r[i]] = Math.max(best[j][l[i]][r[i]], w[i]);
			}
		}
		
		for(int idx = 0; idx < N; idx++) {
			for(int lo = idx; lo >= 0; lo--) {
				for(int hi = idx; hi < N; hi++) {
					if(hi > idx) best[idx][lo][hi] = Math.max(best[idx][lo][hi], best[idx][lo][hi-1]);
					if(lo < idx) best[idx][lo][hi] = Math.max(best[idx][lo][hi], best[idx][lo+1][hi]);
				}
			}
		}

		for(int len = 1; len <= N; len++) {
			for(int lo = 0; lo < N; lo++) {
				int hi = lo + len - 1;
				if(hi >= N) continue;
				
				for(int idx = lo; idx <= hi; idx++) {
					int na = best[idx][lo][hi];
					if(idx > lo) na += dp[lo][idx-1];
					if(idx < hi) na += dp[idx+1][hi];
					dp[lo][hi] = Math.max(dp[lo][hi], na);
				}
			}
		}

		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[0][N-1]);
		//out.println();
		//out.close();
	}
}
