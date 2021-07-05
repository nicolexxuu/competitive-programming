
// http://www.usaco.org/index.php?page=viewproblem2&cpid=359
// "Cow Baseball", 2013 December Bronze Division

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class baseball {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("baseball.in"));
		int N = in.nextInt();
		int[] xs = new int[N];
		for(int i = 0; i < N; i++) {
			xs[i] = in.nextInt();
		}
		
		Arrays.sort(xs);
		in.close();
		
		

		int result = 0;
		
		for(int i = 0; i < N- 2; i++) {
			for(int j = i + 1; j < N - 1; j++) {
				int dist1 = xs[j] - xs[i];
				int minXK = xs[j] + dist1;
				int maxXK = minXK + dist1;
				
				int minK = Arrays.binarySearch(xs, j + 1, N, minXK);
				if(minK < 0) minK = -minK - 1;
				int maxK = Arrays.binarySearch(xs, minK, N, maxXK);
				if(maxK < 0) maxK = -maxK - 1 - 1;
				result += maxK - minK + 1;
			}
		}
		PrintWriter out = new PrintWriter(new File("baseball.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
