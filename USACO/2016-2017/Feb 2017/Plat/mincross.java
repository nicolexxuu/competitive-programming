// Why Did the Cow Cross the Road

import java.util.*;
import java.io.*;

public class mincross {
	
	static int[] BIT;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		BIT = new int[N+1];
		int[] start = new int[N+1];
		int[][] loc = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			start[Integer.parseInt(st.nextToken())] = i;
		}
		
		loc[0][0] = -1;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = start[Integer.parseInt(st.nextToken())];
			loc[from][0] = i;
			loc[from][1] = from;
		}
		br.close();
		
		int[][] sortedLoc = Arrays.copyOf(loc, N+1);
		Arrays.sort(sortedLoc, (a, b) -> a[0] - b[0]);
		
		long intersections = 0;
		long minI = intersections;
		
		// build BIT
		for(int i = N; i >= 1; i--) {
			int from = sortedLoc[i][1];
			intersections += get(from-1);
			update(from, 1);
		}
		
		// shift second row
		for(int i = 1; i <= N; i++) {
			intersections -= sortedLoc[i][1] - 1;
			intersections += N - sortedLoc[i][1];
			
			minI = Math.min(minI, intersections);
		}
		
		// shift first row
		for(int i = 1; i <= N; i++) {
			intersections -= loc[i][0] - 1;
			intersections += N - loc[i][0];
			
			minI = Math.min(minI, intersections);
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(minI);
		//out.println();
		//out.close();
	}
	
	public static void update(int idx, int val) {
		while(idx < BIT.length) {
			BIT[idx] += val;
			idx += idx & -idx;
		}
	}
	
	public static int get(int idx) {
		int sum = 0;
		
		while(idx > 0) {
			sum += BIT[idx];
			idx -= idx & -idx;
		}
		
		return sum;
	}
}
