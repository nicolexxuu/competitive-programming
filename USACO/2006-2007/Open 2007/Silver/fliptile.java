// Fliptile

import java.util.*;
import java.io.*;

public class fliptile {
	static int[] combos;
	static int M, N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int result = Integer.MAX_VALUE;
	static int[][] best, grid;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		grid = new int[M][N];
		best = new int[M][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		br.close();
		
		result = Integer.MAX_VALUE;
		
		int[][] sol = new int[M][N];
		while(sol[0][0] <= 1) {
			check(sol);
			sol[0][N-1]++;
			for(int i = N-1; i > 0; i--) {
				if(sol[0][i] > 1) {
					sol[0][i] -= 2;
					sol[0][i-1]++;
				}
			}
		}
		
		if(result == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		} else {
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					System.out.print(best[i][j]);
					if(j < N-1) System.out.print(" ");
				}
				System.out.println();
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//out.println(result);
		//out.close();
	}
	
	public static void flip(int[][] grid, int r, int c) {
		grid[r][c] = 1 - grid[r][c];
		for(int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if(nr >= 0 && nr < M && nc < N && nc >= 0) grid[nr][nc] = 1 - grid[nr][nc];
		}
	}
	
	public static void check(int[][] sol) {
		int[][] copy = new int[M][N];
		for(int i = 0; i < M; i++) {
			copy[i] = Arrays.copyOf(grid[i], N);
		}
		
		int flips = 0;
		//fix first row first
		for(int i = 0; i < N; i++) {
			if(sol[0][i] == 1) {
				flip(copy, 0, i);
				//System.out.println("flipped 0 " + i);
				flips++;
			}
		}
		
		for(int r = 1; r < M; r++) {
			for(int c = 0; c < N; c++) {
				if(copy[r-1][c] == 1) {
					flip(copy, r, c);
					//System.out.println("flipped " + r + " " + c);
					sol[r][c] = 1;
					flips++;
				} else {
					sol[r][c] = 0;
				}
			}
		}
		
		boolean good = true;
		for(int r = 0; r < M; r++) {
			for(int c = 0; c < N; c++) {
				//System.out.print(copy[r][c] + " ");
				if(copy[r][c] == 1) good = false;
			}
		//System.out.println();
		}
		
		if(good && flips < result) {
			result = flips;
			for(int i = 0; i < M; i++) {
				best[i] = Arrays.copyOf(sol[i], N);
			}
		}
	}
}

