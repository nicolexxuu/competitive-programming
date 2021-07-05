// Cow Travelling

import java.util.*;
import java.io.*;

public class ctravel {
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for(int j = 0; j < M; j++) {
				if(line.charAt(j) == '*') grid[i][j] = 1;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken())-1;
		int c1 = Integer.parseInt(st.nextToken())-1;
		int r2 = Integer.parseInt(st.nextToken())-1;
		int c2 = Integer.parseInt(st.nextToken())-1;
		br.close();
		
		int[][][] dp = new int[N][M][T+1];
		dp[r1][c1][0] = 1;
		
		for(int t = 0; t < T; t++) {
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(grid[r][c] == 1) continue;
					for(int k = 0; k < 4; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
							dp[nr][nc][t+1] += dp[r][c][t];
						}
					}
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[r2][c2][T]);
		//out.println(result);
		//out.close();
	}
}
