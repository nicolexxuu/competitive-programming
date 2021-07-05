// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/index.php?page=viewproblem2&cpid=572
// "Breed Counting", 2015 December Silver Division

import java.util.*;
import java.io.*;

public class bcount {
	public static void main(String[] args) throws IOException {
		String file = "bcount";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[][] ps = new int[N+1][3];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cow = Integer.parseInt(st.nextToken()) - 1;
			for(int j = 0; j < 3; j++) {
				ps[i][j] = ps[i-1][j];
				if(cow==j) ps[i][j]++;
			}
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));

		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < 3; j++) {
				out.print(ps[b][j] - ps[a-1][j]);
				if(j<2) out.print(" ");
			}
			out.println();
		}
		out.close();
		br.close();
		
	}
}
