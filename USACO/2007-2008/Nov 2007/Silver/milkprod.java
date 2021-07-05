// Milking Time

import java.util.*;
import java.io.*;

public class milkprod {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] intervals = new int[M+1][3];
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			intervals[i][0] = Integer.parseInt(st.nextToken());
			intervals[i][1] = Integer.parseInt(st.nextToken()) + R;
			intervals[i][2] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
		
		int[] dp = new int[M+1];
		
		for(int i = 1; i <= M; i++) {
			//(a, b) -> a[0] - b[0]
			dp[i] = dp[i-1];
			int before = Arrays.binarySearch(intervals, new int[] {0, intervals[i][0]}, (a, b) -> a[1] - b[1]);
			if(before < 0) before = (before + 1) * -1-1;
			dp[i] = Math.max(dp[i], dp[before] + intervals[i][2]);
		}
		
		int result = dp[M];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
}

