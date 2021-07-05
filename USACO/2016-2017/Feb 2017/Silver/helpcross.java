
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class helpcross {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("helpcross.in"));
		int C = in.nextInt();
		int N = in.nextInt();
		int[] chTimes = new int[C];
		for(int i = 0; i < C; i++) {
			chTimes[i] = in.nextInt();
		}
		Cow[] cows = new Cow[N];
		for(int i = 0; i < N; i++) {
			cows[i] = new Cow(in.nextInt(), in.nextInt());
		}
		in.close();
		
		Arrays.sort(chTimes);
		Arrays.sort(cows);
		int result = 0;
		int fca = 0; // first cow available
		PriorityQueue<Integer> eCows = new PriorityQueue<>();
		for(int c = 0; c < C; c++) {
			while(!eCows.isEmpty() && eCows.peek() < chTimes[c]) eCows.remove();
			for(int cow = fca; cow < N && cows[cow].start <= chTimes[c]; cow++) {
				if(cows[cow].end >= chTimes[c]) {
					eCows.add(cows[cow].end);
					fca = cow + 1;
				}
			}
			if(!eCows.isEmpty()) {
				eCows.remove();
				result++;
			} 
		}
		PrintWriter out = new PrintWriter(new File("helpcross.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class Cow implements Comparable<Cow>{
		int start;
		int end;
		
		Cow(int s, int e){
			start = s;
			end = e;
		}
		
		public int compareTo(Cow other) {
			return start - other.start;
		}
	}
}
