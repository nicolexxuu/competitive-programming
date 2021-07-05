// The Lazy Cow

import java.util.*;
import java.io.*;

public class lazy {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] rg = new int[N*2][N*2];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int nR = i + j - 1;
				int nC = Math.abs(N-nR);
				int add = 2*(Math.min(j, N-i+1)) -1;
				rg[nR][nC+add] = grid[i][j];
			}
		}
		br.close();

		int[][] ps = new int[N*2][N*2];
		for(int i = 1; i < N*2; i++) {
			for(int j = 1; j < N*2; j++) {
				ps[i][j] = ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1];
				ps[i][j] += rg[i][j];
			}
		}
		
		int res = 0;
		for(int i = 1; i < N*2; i++) {
			for(int j = 1; j < N*2; j++) {
				int u = Math.max(0, i-K*2-1), l = Math.max(j-K*2-1, 0);
				int eat = ps[i][j] + ps[u][l] - ps[u][j] - ps[i][l];
				res = Math.max(res, eat);
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		//out.println();
		//out.close();
	}
}
