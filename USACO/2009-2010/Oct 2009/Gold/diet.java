// Bessie's Weight Problem

import java.util.*;
import java.io.*;

public class diet {
	static int[] weight;
	static int H, N;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[N+1][H+1];
		
		weight = new int[N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
		}
		
		br.close();
		
		int[] dp = new int[H+1];
		
		for(int i = 1; i <= N; i++) {
			for(int c = 1; c <= H; c++) {

				int curr = weight[i];
				if(curr <= c) dp[c] = Math.max(dp[c], dp[c-curr] + curr);
			}
		}
		
		//int result = knapsack(N, H);
		int result = dp[H];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static int knapsack(int i, int c) {
		if(c < 0) return -1000000;
		if(i < 0) return 0;
		// System.out.println("i: " + i + " c: " + c);
		if(dp[i][c] >= 0) return dp[i][c];
			
		int result = Math.max(knapsack(i-1, c), knapsack(i-1, c-weight[i]) + weight[i]);
		dp[i][c] = result;
		return result;
	}

}
