// Cow Hopscotch

import java.util.*;
import java.io.*;

public class hopscotch {
	public static void main(String[] args) throws IOException {
		String file = "hopscotch";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] grid = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		int[][] dp = new int[R][C];
		dp[0][0] = 1;
		
		for(int r = 1; r < R; r++) {
			for(int c = 1; c < C; c++) {
				
				for(int i = 0; i < r; i++) {
					for(int j = 0; j < c; j++) {
						if(grid[i][j] != grid[r][c]) dp[r][c] += dp[i][j];
						dp[r][c] %= 1000000007;
					}
				}
			}
		}
		
		int result = dp[R-1][C-1];
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
