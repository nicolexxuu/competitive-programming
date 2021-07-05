// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://usaco.org/index.php?page=viewproblem2&cpid=571
// 2015 December Silver Division

import java.util.*;
import java.io.*;

public class highcard {
	public static void main(String[] args) throws IOException {
		String file = "highcard";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		HashSet<Integer> e = new HashSet<>();
		int[] elsie = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			e.add(t);
			elsie[i] = t;
		}
		
		Arrays.sort(elsie);
		int[] bessie = new int[N];
		int index = 0;
		for(int i = 1; i <= N*2; i++) {
			if(!e.contains(i)) {
				bessie[index] = i;
				index++;
				if(index == N) break;
			}
		}
		
		br.close();

		int result = 0;
		int bI = 0;
		
		for(int i = 0; i < N; i++) {

			while(bI < N && bessie[bI]<elsie[i]) bI++;
			
			if(bI < N) {
				result++;
				bI++;
			} else {
				break;
			}
		}
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
}
