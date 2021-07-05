// Balanced Cow Breeds

import java.util.*;
import java.io.*;

public class bbreeds {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] p = st.nextToken().toCharArray();
		br.close();
		
		int N = p.length;
		int[] depth = new int[N+1];
		
		depth[0] = 1;
		for(int i = 1; i < N; i++) {
			depth[i] = depth[i-1];
			depth[i] += p[i] == '(' ? 1: -1;
		}
		
		int[][] dp = new int[1001][1001];
		dp[0][0] = 1;
		dp[0][1] = 1;
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(p[i] == '(') {
					if(j > 0) dp[i][j] += dp[i-1][j-1];
					dp[i][j] += dp[i-1][j];
				} else {
					dp[i][j] += dp[i-1][j+1];
					if(depth[i] - j >= 0) dp[i][j] += dp[i-1][j];
				}
				
				dp[i][j] %= 2012;
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[N-1][0]);
		//out.println();
		//out.close();
	}
}
