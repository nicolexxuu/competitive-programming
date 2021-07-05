// 262144

import java.util.*;
import java.io.*;

public class 262144 {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N][60]; //i - start index; j - value; i,j - length
		
		int max = 1;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			max = Math.max(max, a);
			dp[i][a] = 1;
		}
		br.close();
		
		
		for(int g = 1; g < 60 && max >= g - 1; g++) {
			for(int s = 0; s < N; s++) {
				if(dp[s][g] > 0) {
					max = Math.max(max, g);
				} else {
					int len = dp[s][g-1];
					if(len == 0 || s == N-1) continue;
					if(s+len>= N || dp[s+len][g-1] == 0) continue;
					dp[s][g] = len + dp[s+len][g-1];
					max = Math.max(max, g);
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(max);
		//out.println();
		//out.close();
	}
}
