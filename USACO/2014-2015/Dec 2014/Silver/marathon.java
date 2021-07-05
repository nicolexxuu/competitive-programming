// Marathon

import java.util.*;
import java.io.*;

public class marathon {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] cp = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cp[i][0] = Integer.parseInt(st.nextToken());
			cp[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dist = new int[N][N]; //precalculate dist between all pairs
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dist[i][j] = Math.abs(cp[i][0] - cp[j][0]) + Math.abs(cp[i][1] - cp[j][1]);
			}
		}
		br.close();
		
		int[][] dp = new int[N][K+1];
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j <= K; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for(int m = 0; m <= j && m < i; m++) {
					int before = i-m-1;
					dp[i][j] = Math.min(dp[i][j], dp[before][j-m] + dist[before][i]);
				}
			}
		}
		
		int result = dp[N-1][K];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}

