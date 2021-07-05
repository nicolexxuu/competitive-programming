
// http://usaco.org/index.php?page=viewproblem2&cpid=414
// Cow Art

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowart {
	
	static int [] rs = {-1, 0, 0, 1};
	static int [] cs = {0, -1, 1, 0};
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("cowart.in"));
		int N = in.nextInt();
		char[][] painting = new char[N][N];
		for(int i = 0; i < N; i++) {
			painting[i] = in.next().toCharArray();
		}
		in.close();
		
		
		int human = findRegions(painting, N);
		
		for(int i = 0; i < N; i++) {
			for(int r = 0; r < N; r++) {
				if(painting[i][r] == 'R') painting[i][r] = 'G';
			}
		}

		int cow = findRegions(painting, N);
		
		PrintWriter out = new PrintWriter(new File("cowart.out"));
		System.out.println(human + " " + cow);
		out.println(human + " " + cow);
		out.close();
	}
	
	static int findRegions(char[][] painting, int N) {
		boolean [][] visited = new boolean[N][N];
		int count = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(!visited[r][c]) {
					count++;
					floodFill(painting, visited, N, r, c);
				}
			}
		}
		return count;
	}
	
	static void floodFill(char[][] painting, boolean [][] visited, int N, int r, int c) {
		visited[r][c] = true;
		for(int n = 0; n < 4; n++) {
			int i = r + rs[n];
			int j = c + cs[n];
			if(i >= 0 && i < N && j >= 0 && j < N) {
				if(!visited[i][j])
					if(painting[i][j] == painting[r][c] ) {
						floodFill(painting, visited, N, i, j);
					}
			}
		}
		
	}
}
