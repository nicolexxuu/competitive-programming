// Taming the Herd

import java.util.*;
import java.io.*;

public class taming {
	public static void main(String[] args) throws IOException {
		String file = "taming";
		//BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] log = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) log[i] = Integer.parseInt(st.nextToken());
		br.close();
		
		int[][][] dp = new int[N+1][N+1][N+1]; // day, # of breakouts, # days since last breakouts
		
		for(int day = 0; day <= N; day++) {
			for(int bk = 0; bk <= N; bk++) {
				Arrays.fill(dp[day][bk], 200);
			}
		}
		
		dp[0][0][0] = 0;
		
		for(int day = 1; day <= N; day++) {
			dp[day][0][day] = dp[day-1][0][day] + (day-1 == log[day] ? 0 : 1); // no breakouts - if fake, add 1
			
			for(int bk = 1; bk <= day; bk++) {
				// just broke out
				int min = 201;
				for(int i = 0; i <= N; i++) min = Math.min(dp[day-1][bk-1][i], min);
				dp[day][bk][0] = min + (log[day] == 0 ? 0 : 1); // if fake, add 1
				
				
				// last breakout was earlier
				for(int days = 1; days <= day - bk + 1; days++) 
					dp[day][bk][days] = dp[day-1][bk][days-1] + (log[day] == days ? 0 : 1); // if fake, add 1
			}
		}
		
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(int bk = 1; bk <= N; bk++) {
			int min = 200;
			for(int i: dp[N][bk]) min = Math.min(min, i);
			System.out.println(min);
			out.println(min);
		}
		
		out.close();
	}
}
