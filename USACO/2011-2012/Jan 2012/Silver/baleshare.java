// Bale Share

import java.util.*;
import java.io.*;

public class baleshare {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		boolean[][][] dp = new boolean[2][801][801];
		dp[0][0][0] = true;
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			sum += size;
			for(int a = 0; a <= 800; a++) {
				for(int b = 0; b <= 800; b++) {
					if(size <= a) dp[i%2][a][b] |= dp[(i+1)%2][a-size][b];
					if(size <= b) dp[i%2][a][b] |= dp[(i+1)%2][a][b-size];
					dp[i%2][a][b] |= dp[(i+1)%2][a][b];
				}
			}
		}
		
		br.close();
		//System.out.println(sum);
		int result = Integer.MAX_VALUE;
		for(int a = 0; a <= 800; a++) {
			for(int b = 0; b <= 800; b++) {
				if(dp[0][a][b] || dp[1][a][b]) result = Math.min(result, Math.max(a, Math.max(b, sum-a-b)));
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
