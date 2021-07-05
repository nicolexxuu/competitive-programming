// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=762
// "My Cow Ate My Homework," 2017 December Silver Division

import java.util.*;
import java.io.*;

public class homework {
	public static void main(String[] args) throws IOException {
		String file = "homework";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] scores = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			//System.out.println("i: " + i);
			int a = Integer.parseInt(st.nextToken());
			scores[i] = a;
			System.out.println(a);
		}
		br.close();
		long[] sums = new long[N];
		long best = 0;
		int min = scores[N-1];
		long sum = scores[N-1];
		
		for(int i = N-2; i>=1; i--) {
			min = Math.min(min, scores[i]);
			sum += scores[i];
			
			sums[i] = sum - min;
			best = Math.min(best, (sum) * (N-i));
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("homework.out"))));
		for(int i = 1; i < N-2; i++) {
			if(sums[i]* (N-i) == best) {
				System.out.println(i);
				out.println(i);
			}
		}
		out.close();
	}
}
