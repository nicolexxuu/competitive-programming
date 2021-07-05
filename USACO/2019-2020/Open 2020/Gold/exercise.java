// Exercise

import java.util.*;
import java.io.*;

public class exercise {
	public static void main(String[] args) throws IOException {
		String file = "exercise";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		br.close();
		
		ArrayList<Integer> primes = new ArrayList<>();
		boolean[] composite = new boolean[N+1];
		for(int i = 2; i <= N; i++) {
			if(!composite[i]) {
				primes.add(i);
				for(int j = 2; j * i <= N; j++) {
					composite[j * i] = true;
				}
			}
		}
		
		long[][] dp = new long[N+1][1230]; // n - value of n, p - first p prime values
		for(int i = 0; i <= N; i++) dp[i][0] = 1;
		
		for(int n = 0; n <= N; n++) {
			for(int p = 1; p <= primes.size(); p++) {
				int pr = primes.get(p-1);
				dp[n][p] = dp[n][p-1];
				
				while(pr <= n) {
					dp[n][p] += (pr * dp[n-pr][p-1] % M);
					dp[n][p] %= M;
					pr *= primes.get(p-1);
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[N][primes.size()]);
		out.println(dp[N][primes.size()]);
		out.close();
	}
}
