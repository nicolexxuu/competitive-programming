// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/index.php?page=viewproblem2&cpid=918
// "Sleepy Cow Herding," 2019 February Silver Division

import java.util.*;
import java.io.*;

public class herding {
	public static void main(String[] args) throws IOException {
		String file = "herding";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] locs = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			locs[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(locs);
		br.close();

		int min = N;
		int max = 0;
		int l = 0, r = 0;
		int cows = 1;
		
		while(l < N && r < N) {
			while(locs[r] - locs[l] < N && r < N-1) {
				r++;
				cows++;
			}
			int moves = N - cows;
			min = Math.min(min, moves);
			max = Math.max(max, moves);
			l++;
			cows--;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(min);
		System.out.println(max);
		out.println(min);
		out.println(max);
		out.close();
	}
}
