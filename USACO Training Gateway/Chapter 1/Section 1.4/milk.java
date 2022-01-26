/*
ID: diudiux1
LANG: JAVA
TASK: milk
 */

import java.util.*;
import java.io.*;

public class milk {
	public static void main(String[] args) throws IOException {
		String file = "milk";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] farmers = new int[M][2];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			farmers[i][0] = Integer.parseInt(st.nextToken());
			farmers[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(farmers, (a, b) -> a[0] - b[0]);
		br.close();
		
		long total = 0;
		for(int idx = 0; N > 0; idx++) {
			int units = Math.min(N, farmers[idx][1]);
			total += farmers[idx][0] * units;
			N -= units;
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(total);
		out.println(total);
		out.close();
	}
}