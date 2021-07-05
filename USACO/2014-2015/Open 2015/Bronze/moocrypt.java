
// http://www.usaco.org/index.php?page=viewproblem2&cpid=545
// "Moocryption"

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moocrypt {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("moocrypt.in"));
		int N = in.nextInt();
		int M = in.nextInt();
		char[][] puzzle = new char[N][];
		for(int i = 0; i < N; i++) {
			puzzle[i] = in.next().toCharArray();
		}
		in.close();
		
		int result = 0;
		
		for(char l1 = 'A'; l1 <= 'Z'; l1++) {
			for(char l2 = 'A'; l2 <= 'Z'; l2++) {
				if(l1 == 'M') l1++;
				if(l2 == 'O') l2++;
				if(l1 == l2) l2++;
				
				int temp = 0;
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						
						if(puzzle[r][c] == l1) {
							for(int i = r - 1; i <= r + 1; i++) {
								for(int j = c - 1; j <= c + 1; j++) {
									if(i >= 0 && i < N && j >= 0 && j < M && !(i == r && j == c)) {
										if(puzzle[i][j] == l2) {
											int row = i;
											int col = j;
											
											if(i == r - 1) {
												row--;
											}else if (i == r + 1) {
												row++;
											}
											
											if(j == c - 1) {
												col--;
											}else if (j == c + 1) {
												col++;
											}
											
											if(row >= 0 && row < N && col >= 0 && col < M) {
												if(puzzle[row][col] == l2) {
													temp++;
												}
												
											}
										}
									}
								}
							}
						}
						
					}
				}
				
				result = Math.max(result,  temp);
			}
		}

		
		PrintWriter out = new PrintWriter(new File("moocrypt.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}

}
