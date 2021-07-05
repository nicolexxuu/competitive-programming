// Stamp Painting

import java.util.*;
import java.io.*;

public class spainting {
	public static void main(String[] args) throws IOException {
		String file = "spainting";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		int MOD = 1000000007;
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		br.close();
		
		long[] pow = new long[N+1];
		long[] dp = new long[N+1];
		long[] ps = new long[N+1];
		pow[0] = dp[0] = ps[0] = 1;
		for(int i = 1; i <= N; i++) pow[i] = (pow[i-1] * M) % MOD;
		
		for(int i = 1; i < K; i++) {
			dp[i] = pow[i];
			ps[i] = (ps[i-1] + dp[i]) % MOD;
		}
		
		for(int i = K; i <= N; i++) {
			dp[i] = (ps[i-1] - ps[i-K]) * (M-1) % MOD;
			ps[i] = (ps[i-1] + dp[i]) % MOD;
		}
		
		long res = (pow[N] - dp[N]) % MOD;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		out.println(res);
		out.close();
	}
}
