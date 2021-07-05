// Candy

import java.util.*;
import java.io.*;

public class candy {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int NO = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = N + F*M + 1;
		
		int[] dp = new int[L], checked = new int[L];
		boolean[] fav = new boolean[L];
		
		int[] candies = new int[NO];
		for(int i = 0; i < NO; i++) {
			st = new StringTokenizer(br.readLine());
			candies[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			fav[Integer.parseInt(st.nextToken())] = true;
		}
		br.close();
		
		boolean cycle = false;
		
		 // is this knapsack? switch order of loops
		Arrays.fill(dp, -1);
		dp[N] = 0;
		for(int i = N-1; i >= 0; i--) {
			checked[i]++;
			if(checked[i] > F+2) {
				cycle = true;
				break;
			}
			
			for(int candy: candies) {
				int from = i+candy;
				if(from >= dp.length) continue;
				if(dp[from] != -1) dp[i] = Math.max(dp[i], dp[from] + candy);
			}
			
			if(fav[i] && dp[i] > dp[i+M]) {
				dp[i+M] = dp[i];
				i = i+M+1;
			}
		}
		
		int max = 0; 
		for(int i: dp) max = Math.max(max, i);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		if(cycle) System.out.println(-1);
		else System.out.println(max);
		//out.println(result);
		//out.close();
	}
}
