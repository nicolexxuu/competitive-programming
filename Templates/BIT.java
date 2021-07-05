// Binary Index Tree Implementation

import java.util.*;
import java.io.*;

public class BIT {
	
	static int[] BIT;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		BIT = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i-1] = Integer.parseInt(st.nextToken());
			add(i, A[i-1]);
		}
	}
	
	// update prefix sums of array
	// O(N log N)
	public static void add(int idx, int val) {
		// -idx = !idx + 1 => 2's complement
		// idx & -idx => rightmost 1
		while(idx < BIT.length) {
			BIT[idx] += val;
			idx += idx & -idx;
		}
	}
	
	// get prefix sum of array at position idx
	// O(N log N)
	public static int get(int idx) {
		int sum = 0;
		
		while(idx > 0) {
			sum += BIT[idx];
			idx -= idx & -idx;
		}
		
		return sum;
	}
}
