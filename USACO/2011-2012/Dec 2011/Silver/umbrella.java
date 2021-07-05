// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=99
// "Umbrellas for Cows", 2011 December Silver Division

import java.util.*;
import java.io.*;

public class umbrella {
	public static void main(String[] args) throws IOException {
		String file = "umbrella";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[N];
		int[] costs = new int[M + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cows);
		
		br.close();
		
		int[] dp = new int[N];
		dp[0] = costs[1];
		for(int i = 1; i < N; i++) {
			dp[i] = dp[i-1] + costs[1];
		}
		
		for(int i=0; i<N; i++) {
			System.out.print("from " + i + " to ");
			int before = 0;
			if(i>0) before = dp[i-1];
//			for(int j = cows[ii+1; j<N; j++) {
//				System.out.println(j);
//				dp[j] = Math.min(dp[j], before + costs[cows[j]-cows[i]+1]);
//			}
		}
		
		System.out.println("hi");
		int result = dp[N-1];
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
