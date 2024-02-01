// 

import java.util.*;
import java.io.*;

public class radio {
	static int[][] f, b;
	public static void main(String[] args) throws IOException {
		String file = "radio";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		f = new int[N+1][2];
		b = new int[M+1][2];
		st = new StringTokenizer(br.readLine());
		f[0][0] = Integer.parseInt(st.nextToken());
		f[0][1] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		b[0][0] = Integer.parseInt(st.nextToken());
		b[0][1] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		String fPath= st.nextToken();
		for(int i = 1; i <= N; i++) {
			f[i][0] = f[i-1][0]; f[i][1] = f[i-1][1];
			if(fPath.charAt(i-1) == 'N') f[i][1]++;
			if(fPath.charAt(i-1) == 'E') f[i][0]++;
			if(fPath.charAt(i-1) == 'S') f[i][1]--;
			if(fPath.charAt(i-1) == 'W') f[i][0]--;
		}
		
		st = new StringTokenizer(br.readLine());
		String bPath= st.nextToken();
		for(int i = 1; i <= M; i++) {
			b[i][0] = b[i-1][0]; b[i][1] = b[i-1][1];
			if(bPath.charAt(i-1) == 'N') b[i][1]++;
			if(bPath.charAt(i-1) == 'E') b[i][0]++;
			if(bPath.charAt(i-1) == 'S') b[i][1]--;
			if(bPath.charAt(i-1) == 'W') b[i][0]--;
		}
		br.close();
		
		int[][] dp = new int[N+1][M+1];
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= M; j++) {
				if(i == 0 && j == 0) continue;
				dp[i][j] = Integer.MAX_VALUE;
				if(i > 0 && j > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
				if(i > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
				if(j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
				dp[i][j] += dist(i, j);
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[N][M]);
		out.println(dp[N][M]);
		out.close();
	}
	
	public static int dist(int i, int j) {
		return (f[i][0] - b[j][0]) * (f[i][0] - b[j][0]) + (f[i][1] - b[j][1]) * (f[i][1] - b[j][1]);
	}
}