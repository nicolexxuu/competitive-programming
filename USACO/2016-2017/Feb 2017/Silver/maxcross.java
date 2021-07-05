
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://www.usaco.org/index.php?page=viewproblem2&cpid=715
//"Why Did The Cow Cross The Road II", 2017 February Silver Division

import java.util.*;
import java.io.*;

public class maxcross {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("maxcross.in"));
		int N = in.nextInt();
		int K = in.nextInt();
		int B = in.nextInt();
		boolean [] broken = new boolean[N + 1];
		for(int i = 1; i <= B; i++) {
			broken[in.nextInt()] = true;
		}
		in.close();

		int result = K;
		int currCount = 0;
		for(int cross = 1; cross <= K; cross++) {
			if(broken[cross]) {
				currCount++;
			}
		}
		result = Math.min(result, currCount);
		if(broken[1]) currCount--;
		
		for(int cross = 2; cross <= N - K + 1; cross++) {
			if(broken[cross + K - 1]) currCount++;
			result = Math.min(result, currCount);
			if(broken[cross]) currCount--;
		}
		
		PrintWriter out = new PrintWriter(new File("maxcross.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}


/* ANALYSIS

1 2 3 4 5 6 7 8 9 10
x x     x       x x



*/
