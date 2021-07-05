// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moobuzz {
	public static void main(String[] args) throws IOException {
		String file = "moobuzz";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		br.close();
		
		//binary search for number that has N non-moo numbers
		long low = 0;
		long high = Integer.MAX_VALUE;
		while (low < high) {
			long mid = (low + high) / 2; //the number
			//System.out.println(mid);
			long nm = nonMoo(mid);
			//System.out.println("non moos: " + nm);
			if (nm >= N) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(low);
		out.println(low);
		out.close();
	}
	
	public static long nonMoo(long n) {
		long moos = n/3 + n/5 - n/15;
		return n - moos;
	}
}


/* ANALYSIS


	how many multiples of 3 before N? threes
	how many multiples of 5 before N? fives
	how many multiples of 15 before N? fifteens
	threes + fives - fifteens
	how many moo numbers ^
	
	how many regular numbers: number minus that
	up to M: M - 


*/
