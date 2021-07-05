//  Selfish Grazing

import java.util.*;
import java.io.*;

public class sgraze {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] cows = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i][0] = Integer.parseInt(st.nextToken());
			cows[i][1] = Integer.parseInt(st.nextToken());
		}
		
		br.close();
		
		Arrays.sort(cows, (a, b) -> a[1] - b[1]);

		int[] dp = new int[N+1];
		for(int i = 1; i <= N; i++) dp[i] = Math.max(dp[i-1], dp[bs(cows, cows[i][0])]+1);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[N]);
		//out.println(result);
		//out.close();
	}
	
	public static int bs(int[][] cows, int target) {
		int lo = 0, hi = cows.length-1;
		while(lo < hi) {
			int mid = (lo+hi+1)/2;
			int val = cows[mid][1];
			if(val > target) hi = mid-1;
			else lo = mid;
		}
		return lo;
	}
}

