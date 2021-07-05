// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/index.php?page=viewproblem2&cpid=1038
// "Social Distancing," 2020 US Open Silver Division

import java.util.*;
import java.io.*;

public class socdist {
	public static void main(String[] args) throws IOException {
		String file = "socdist";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[][] intervals = new long[M][2];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			intervals[i][0] = Long.parseLong(st.nextToken());
			intervals[i][1] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(intervals, (x, y) -> (x[0] < y[0]) ? -1 : 1);
		br.close();

		long lo = 1;
		long hi = intervals[M-1][1] - intervals[0][0];
		
		while(lo < hi) {
			long mid = (lo+hi+1)/2;
			//System.out.println("mid: " + mid);
			int cows = 0;
			long currPos = intervals[0][0];
			for(int i = 0; i < M; i++) {
				currPos = Math.max(currPos, intervals[i][0]);
				while(currPos<=intervals[i][1]) {
					//System.out.println(currPos);
					cows++;
					currPos += mid;
				}
				if(cows >= N) break;
			}
			
			if(cows >= N) {
				lo = mid;
			} else {
				hi = mid - 1;
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(hi);
		out.println(hi);
		out.close();
	}
}

/* ANALYSIS





*/
