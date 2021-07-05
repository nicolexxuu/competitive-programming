// Talent Show
// easier way: calculate max talent for each weight and then find best ratio (http://www.cs.ucf.edu/~dmarino/progcontests/mysols/highschool/usaco/2017/gold/talent.java)

import java.util.*;
import java.io.*;

public class talent {
	static int[] w, t;
	static int N, W;
	public static void main(String[] args) throws IOException {
		String file = "talent";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		w = new int[N];
		t = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			t[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		long lo = 0, hi = 1000 * 1000 * N;
		while(lo < hi) {
			long mid = (lo + hi + 1)/2;
			if(possible(mid)) {
				lo = mid;
			} else {
				hi = mid - 1;
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(hi);
		out.println(hi);
		out.close();
	}
	
	public static boolean possible(long x) {
		long[] dp = new long[W+1];
		Arrays.fill(dp, Long.MIN_VALUE);
		dp[0] = 0;
		for(int i = 0; i < N; i++) {
			long adj = 1000 * t[i] - w[i] * x;
			for(int j = W; j >= 0; j--) {
				if(dp[j] != Long.MIN_VALUE) {
					int idx = Math.min(W, j + w[i]);
					dp[idx] = Math.max(dp[idx], dp[j] + adj);
				}
			}
			
		}
		
		return dp[W] > 0;
	}
}
