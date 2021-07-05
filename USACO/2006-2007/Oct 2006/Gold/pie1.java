
// Cow Pie Treasures

import java.util.*;
import java.io.*;

public class pie1 {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file +
		// ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] dp = new int[R+2][C+1];

		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
				if(i > j) dp[i][j] = 0;
			}
		}
		br.close();

		for (int c = 2; c <= C; c++) {
			for (int r = 1; r <= R; r++) {
				dp[r][c] += Math.max(dp[r][c-1], Math.max(dp[r-1][c-1], dp[r+1][c-1]));
			}
		}

		// PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new
		// File(file + ".out"))));
		System.out.println(dp[R][C]);
		// out.println(result);
		// out.close();
	}
}
