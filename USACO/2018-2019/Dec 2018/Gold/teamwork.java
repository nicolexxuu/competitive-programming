// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=862
// "Teamwork", 2018 December Gold Division

import java.util.*;
import java.io.*;

public class teamwork {
	public static void main(String[] args) throws IOException {
		String file = "teamwork";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] cows = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		int[] dp = new int[N+1];
		dp[0] = 0;
		
		for(int i = 1; i <= N; i++) {
			int max = cows[i];
			for(int j = i; j > 0 && i-j+1 <= K; j--) {
				max = Math.max(max, cows[j]);
				dp[i] = Math.max(dp[i], dp[j-1] + max*(i-j+1));
			}
		}
		
		int result = dp[N];
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
}


