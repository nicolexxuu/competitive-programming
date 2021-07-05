// Building A Fence

import java.util.*;
import java.io.*;

public class quad {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		br.close();
		
		int[][] dp = new int[5][N+1];
		dp[0][0] = 1;
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= N; j++) {
				for(int len = 1; len < (N+1)/2 && j-len >= i-1; len++) {
					dp[i][j] += dp[i-1][j-len];
				}
			}
		}
		
		int result = dp[4][N];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
