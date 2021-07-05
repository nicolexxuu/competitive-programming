// Cow IDs

import java.util.*;
import java.io.*;

public class cowids {
	static int[][] dp;
	static boolean leadingZero = true;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		br.close();
		
		dp = new int[5000][K+1];
		if(K == 1) {
			System.out.print(1);
			for(int i = 1; i < N; i++) {
				System.out.print(0);
			}
			System.out.println();
		} else {
			dp[0][1] = 1;
			for(int i = 0; i < 5000; i++) dp[i][0] = 1;
			for(int i = 1; i < 5000; i++) {
				for(int j = 1; j <= K; j++) {
					dp[i][j] = Math.min(dp[i-1][j] + dp[i-1][j-1], 10000000);
				}
			}
			
			
			printSol(N, K, 5000);
			
			
		}

	}
	
	public static void printSol(int n, int k, int m)
	{
		 if (k==0 && n <= 0) return;
		 if (k==0 || dp[m-1][k] >= n) {
			 if (!leadingZero) System.out.print("0");
			 printSol(n,k,m-1);
		 } else {
			 leadingZero = false;
			 System.out.print("1");
			 printSol(n-dp[m-1][k], k-1, m-1);
		 }
	}
}


