// Scales

import java.util.*;
import java.io.*;

public class scales {
	static int[] mass;
	public static void main(String[] args) throws IOException {
		String file = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		mass = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			mass[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(mass);
		br.close();

		int result = recur(N-1, C);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static int recur(int n, int remaining) {
		if(n < 0) return 0;
		if(mass[n] > remaining) {
			return recur(n-1, remaining);
		} else {
			if(n==0 || n==1 || mass[n-1] + mass[n] <= remaining) {
				return mass[n] + recur(n-1, remaining - mass[n]);
			}
			
			return Math.max(recur(n-1, remaining), mass[n] + recur(n-1, remaining - mass[n]));
			
		}
	}
}

