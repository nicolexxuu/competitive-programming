
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=104
// "Haybale Stacking" 2012 January, Bronze Division

import java.util.*;
import java.io.*;

public class stacking {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("stacking.in"));
		int N = in.nextInt();
		int K = in.nextInt();
		int [] relChanges = new int[N + 1];
		for(int i = 1; i <= K ; i++) {
			int A = in.nextInt();
			int B = in.nextInt();
			relChanges[A]++;
			if(B < N) relChanges[B + 1]--;
		}
		in.close();
		
		int[] stacks = new int[N + 1];
		for(int bale = 1; bale <= N; bale++) {
			stacks[bale] = stacks[bale - 1] + relChanges[bale];
			//System.out.print(stacks[bale] + " ");
		}
		
		Arrays.sort(stacks);

		int result = stacks[(N / 2) + 1];
		PrintWriter out = new PrintWriter(new File("stacking.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
