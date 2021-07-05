
//http://www.usaco.org/index.php?page=viewproblem2&cpid=112
//"Rope Folding"

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class folding {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("folding.in"));
		int N = in.nextInt();
		int L = in.nextInt();
		int[] knots = new int[N];
		for(int i = 0; i < N; i++) {
			knots[i] = in.nextInt();
		}
		Arrays.sort(knots);
		in.close();

		int result = 0;
		
		//check two types of knots separately
		for(int topKnot = 0; topKnot < N - 1; topKnot++) {
			//check if rope can fold in between two points
			double mid = (knots[topKnot] + knots[topKnot + 1]) / 2;
//			System.out.println("fold " + mid);
			boolean matching = true;
			for(int i = 0; i < Math.min(topKnot + 1, N - 1 - topKnot); i++) {
//				System.out.println(Math.abs(mid - knots[topKnot - i]) + " " + Math.abs(mid - knots[topKnot + 1 + i]));
				if(Math.abs(mid - knots[topKnot - i]) != Math.abs(mid - knots[topKnot + 1 + i])) {
					matching = false;
					break;
				}
				
			}
			if(matching) result++;
			
			//check if rope can fold on point itself
			if(topKnot > 0) {
				int fold = topKnot;
//				System.out.println("fold " + fold);
				matching = true;
				for(int i = 0; i < Math.min(topKnot, N - 1 - topKnot); i++) {
//					System.out.println(Math.abs(fold- knots[topKnot - i]) + " " + Math.abs(fold - knots[topKnot + 1 + i]));
					if(Math.abs(fold - knots[fold - i]) != Math.abs(fold - knots[topKnot + 1 + i])) {
						matching = false;
						break;
					}
					
				}
			}
			if(matching) result++;
		}
		PrintWriter out = new PrintWriter(new File("folding.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
