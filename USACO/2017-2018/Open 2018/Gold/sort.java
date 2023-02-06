// Out of Sorts

import java.util.*;
import java.io.*;

public class sort {
	static int[] BIT;
	
	public static void main(String[] args) throws IOException {
		String file = "sort";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		BIT = new int[N+1];
		int[][] A = new int[N+1][2], a = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = new int[]{Integer.parseInt(st.nextToken()), i};
			a[i] = Arrays.copyOf(A[i], 2);
		}
		
		Arrays.sort(a, 1, N+1, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
		for(int i = 1; i <= N; i++) A[a[i][1]][1] = i;
		br.close();
		
		int max = 1;
		for(int i = 1; i <= N; i++) {
			add(A[i][1], 1);
			max = Math.max(max, get(N) - get(i));
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(max);
		out.println(max);
		out.close();
	}
	
	public static void add(int idx, int val) {
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