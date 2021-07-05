import java.util.*;
import java.io.*;

public class convention {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("convention.in"));
		PrintWriter out = new PrintWriter(new File("convention.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] cows = new int[N];
		int maxTime = 0;
		for(int i = 0; i < N; i++) {
			int t = Integer.parseInt(st.nextToken());
			cows[i] = t;
			maxTime = Math.max(maxTime,  t);
		}
		
		Arrays.sort(cows);
		int result = lowerBound(cows, N, M, C);
		
		out.println(result);
		System.out.println(result);
		out.close();
	}
	
	public static int lowerBound(int[] cows, int N, int M, int C) {

		int low = 0;
		int high = 1000000000;
		while(low < high) {
			int maxTime = (low + high) / 2;
			//simulate.. can also search for latest cow that fulfills bus capacity and maxTime
			int buses = 1;
			int bus = 0;
			int firstCowWaiting = cows[0];
			for(int i = 0; i < cows.length; i++) {
				if(i == 0 ||(bus < C && cows[i] - firstCowWaiting <= maxTime)) {
					bus++;
				} else {
					bus = 1;
					buses++;
					firstCowWaiting = cows[i];
				}
			}
			
			if(buses <= M) {
				high = maxTime;
			} else {
				low = maxTime + 1;
			}
		}
		
		return low;
	}
	
	/*
	 *  6 3 2
		1 1 10 14 4 3
	 * 
	 * 
	 * binary search and guess the maximum waiting times of cows
	 * and check if the number of buses is less than M
	 * 
	 */
}
