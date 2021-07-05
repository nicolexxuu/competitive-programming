// The County Fair

import java.util.*;
import java.io.*;

public class cfr {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] P = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] T = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				T[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();

		int[] dp = new int[N];
		for(int i = 0; i < N; i++) if(P[i] >= T[0][i]) dp[i] = 1;
		
		for(int n = 0; n < N; n++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i != j && dp[i] > 0 && dp[j] <= dp[i] && P[i] + T[i][j] <= P[j]) {
						dp[j] = dp[i]+1;
					}
				}
			}
		}
		
		int result = 0;
		for(int i: dp) result = Math.max(i, result);
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
