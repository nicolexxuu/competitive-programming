// Moortal Cowmbat

import java.util.*;
import java.io.*;

public class cowmbat {
	public static void main(String[] args) throws IOException {
		String file = "cowmbat";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		String S = " " + st.nextToken();
		int[] combo = new int[N+1];
		for(int i = 0; i <= N; i++) combo[i] = S.charAt(i) - 'a';
		
		int[][] dist = new int[M][M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) dist[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int k = 0; k < M; k++)
			for(int i = 0; i < M; i++)
				for(int j = 0; j < M; j++) dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
		
		br.close();
		int[][] ps = new int[N+1][M];
		for(int i = 1; i <= N; i++) 
			for(int j = 0; j < M; j++) ps[i][j] = dist[combo[i]][j] + ps[i-1][j];
		
		int[][] dp = new int[N+1][M];
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				dp[i][j] = dp[i-1][j] + dist[combo[i]][j];
				if(i >= 2*K) for(int k = 0; k < M; k++) dp[i][j] = Math.min(ps[i][j] - ps[i-K][j] + dp[i-K][k], dp[i][j]);
			}
		}

		int result = Integer.MAX_VALUE;
		for(int i: dp[N]) result = Math.min(result, i);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
