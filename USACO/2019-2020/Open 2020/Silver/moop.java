// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1040
// "The Moo Particle", 2020 US Open Silver Division

import java.util.*;
import java.io.*;

public class moop {
	public static void main(String[] args) throws IOException {
		String file = "moop";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] p = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			p[i][0] = Integer.parseInt(st.nextToken());
			p[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(p, (x, y) -> (x[0] == y[0]) ?  x[1] - y[1] : x[0] - y[0]);
		br.close();

		int result = 1;
		
		int[] maxY = new int[N];
		maxY[N-1] = p[N-1][1];
		for(int i = N-2; i >= 0; i--) {
			maxY[i] = Math.max(maxY[i+1], p[i][1]);
		}
		
		int[] minY = new int[N];
		minY[0] = p[0][1];
		for(int i = 1; i < N; i++) {
			minY[i] = Math.min(minY[i-1], p[i][1]);
		}
		
		for(int i = 0; i < N-1; i++) {
			if(minY[i] > maxY[i+1]) result++;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
