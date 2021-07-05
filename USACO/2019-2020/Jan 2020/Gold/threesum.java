// Farmer John Solves 3Sum

import java.util.*;
import java.io.*;

public class threesum {
	public static void main(String[] args) throws IOException {
		String file = "threesum";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		long[][] dp = new long[N][N];
		int[] count = new int[2000005];
		
		for(int s = 0; s < N; s++) {
			for(int e = s+1; e < N; e++) {
				int f = -A[s] - A[e];
				if(f >= -1000000 && f <= 1000000) dp[s][e] = count[f + 1000000];
				count[A[e] + 1000000]++;
			}
			for(int i = s+1; i < N; i++) count[A[i] + 1000000] = 0;
		}
		
		for(int s = N-1; s >= 0; s--)
			for(int e = s+2; e < N; e++)
				dp[s][e] += dp[s+1][e] + dp[s][e-1] - dp[s+1][e-1];
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			//System.out.println(dp[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken()) - 1]);
			out.println(dp[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken()) - 1]);
		}
		
		out.close();
		br.close();
	}
}