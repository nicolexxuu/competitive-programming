import java.util.*;
import java.io.*;

public class mooroute {
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		long[] fact = new long[1000001];
		fact[0] = 1;
		for(int i = 1; i <= 1000000; i++) fact[i] = (i * fact[i-1]) % MOD;
		br.close();
		
		long[] dp = new long[N];
		dp[N-1] = 1;
		for(int i = N-2; i >= 0; i--) {
			if(A[i+1] >= A[i]) dp[i] = dp[i+1] * choose(fact, A[i+1]/2-1, A[i]/2-1) % MOD;
			else dp[i] = dp[i+1] * choose(fact, A[i]/2, A[i+1]/2) % MOD;
		}
		
		System.out.println(dp[0]);
	}
	
	public static long choose(long[] fact, int n, int m) {
		return fact[n] * binpow((fact[m] * fact[n-m]) % MOD, MOD-2, MOD) % MOD;
	}
	
	public static long binpow(long a, long b, long m) {
		a %= m;
		long res = 1;
		while(b > 0) {
			if(b % 2 == 1) res = res * a % m;
			a = a * a % m;
			b /= 2;
		}
		
		return res;
	}
}