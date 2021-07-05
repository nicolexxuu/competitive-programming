
//http://usaco.org/index.php?page=viewproblem2&cpid=716
//"Why Did the Cow Cross The Road", 2017 February Contest

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class countcross {
	
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static int M;
	static int[][] grid;
	
	public static void main(String[] args) throws FileNotFoundException {
		//double-sized array
		Scanner in = new Scanner(new File("countcross.in"));
		int N = in.nextInt();
		int K = in.nextInt();
		int R = in.nextInt();
		
		M = N * 2 - 1; //new size of array
		
		grid = new int[M][M];
		
		//both even: cow (1)/ field (0) / visited(-1)
		//even/odd: fence (2)
		//both odd: diagonal
		
		for(int r = 0; r < R; r++) {
			int r1 = in.nextInt() - 1;
			int c1 = in.nextInt() - 1;
			int r2 = in.nextInt() - 1;
			int c2 = in.nextInt() - 1;
			
			int i, j;
			if(r1 == r2) {
				i = r1 * 2;
				j = c1 + c2;
			} else {
				i = r1 + r2;
				j = c1 * 2;
			}
			grid[i][j] = 2;
		}
		
		for(int i = 0; i < K; i++) {
			int r = in.nextInt() - 1;
			int c = in.nextInt() - 1;
			grid[r * 2][c * 2] = 1;
		}
		in.close();
		ArrayList<Integer> sections = new ArrayList<Integer>();
		int totalCows = 0;
		
		for(int r = 0; r < M; r += 2) {
			for(int c = 0; c < M; c += 2) {
				if(grid[r][c] == 1) {
					int count = 1;
					grid[r][c] = -1;
					count += floodFill(r, c);
					sections.add(count);
					totalCows += count;
				}
			}
		}

		int result = 0;
		for(Integer i: sections) {
			result += (totalCows - i) * i;
		}
		PrintWriter out = new PrintWriter(new File("countcross.out"));
		System.out.println(result / 2);
		out.println(result / 2);
		out.close();
	}
	
	public static int floodFill(int r, int c) {
		int count = 0;
		for(int d = 0; d < 4; d++) {
			int r1 = r + dr[d];
			int c1 = c + dc[d];
			int r2 = r1 + dr[d];
			int c2 = c1 + dc[d];
			if(r2 >= 0 && r2 < M && c2 >= 0 && c2 < M) {
				if(grid[r1][c1] != 2 && grid[r2][c2] != -1) {
					if(grid[r2][c2] == 1) count++;
					grid[r2][c2] = -1;
					count += floodFill(r2, c2);
				}
				
			}
		}
		return count;
	}
}
