// Hay For Sale

import java.util.*;
import java.io.*;

public class hay4sale {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] vol = new int[H+1];
		for(int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			vol[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int[] dp = new int[C+1];
		for (int i = 1; i <= H; i++) {
			for(int c = C; c >= vol[i]; c--) {
				dp[c] = Math.max(dp[c], vol[i] + dp[c-vol[i]]);
			}
		}
		
		int result = dp[C];
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static void printArr(int[] a) {
		for(int i: a) System.out.print(i + " ");
		System.out.println();
	}
}
