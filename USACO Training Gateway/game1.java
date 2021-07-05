// A Game

import java.util.*;
import java.io.*;

public class game1 {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		int[] board = new int[N+1];
		int[] sum = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			board[i] = in.nextInt();
			sum[i] = board[i] + sum[i-1];
		}
		
		int[][] dp = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) dp[i][i] = board[i];
		
		for(int i = N; i >= 1; i--) {
			for(int j = i; j <= N; j++) {
				if(i == j) continue;
				dp[i][j] = Math.max(sum[j] - sum[i-1] - dp[i][j-1], sum[j] - sum[i-1] - dp[i+1][j]);
			}
		}
		int result = dp[1][N];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result + " " + (sum[N] - sum[0] - result));
		//out.println(result);
		//out.close();
	}
}

