// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=993
// "Time is Money", 2020 January Gold Division

import java.util.*;
import java.io.*;

public class time {
	public static void main(String[] args) throws IOException {
		String file = "time";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] moonies = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			moonies[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
		}
		
		br.close();
		
		int[][] dp = new int[N+1][1001];
		dp[1][0] = 0;
		for(int t = 0; t < 1000; t++) { //day before you get there
			for(int start = 1; start <= N; start++) {
				if(dp[start][t] > 0 || (start == 1 && t == 0))
				for(int i: adj.get(start)) {
					dp[i][t+1] = Math.max(dp[i][t+1], dp[start][t] + moonies[i]);
				}
			}
		}
		
		int result = 0;
		for(int i = 0; i < 1001; i++) {
			result = Math.max(result, dp[1][i] - (C * i * i));
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("time.out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}

