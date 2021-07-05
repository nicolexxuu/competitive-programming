// Why Did the Cow Cross the Road II

import java.util.*;
import java.io.*;

public class nocross {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		int[] B = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			B[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int[][] dp = new int[N][N];
		if(Math.abs(A[0] - B[0]) <= 4) dp[0][0] = 1;
		
		for(int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][0];
			if(Math.abs(A[i]-B[0])<=4) dp[i][0] = Math.max(dp[i][0], 1);
		}
		
		for(int i = 1; i < N; i++) {
			dp[0][i] = dp[0][i-1];
			if(Math.abs(A[0]-B[i])<=4) dp[0][i] = Math.max(dp[0][i], 1);
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < N; j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(Math.abs(A[i]-B[j])<=4) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
			}
		}
		
		int result = dp[N-1][N-1];
		//PrintWriter out = new -1PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
