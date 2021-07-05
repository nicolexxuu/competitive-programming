import java.util.*;
import java.io.*;

public class bovgenetics {
	static long MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		int N = str.length();
		long[] genome = new long[N];
		for(int i = 0; i < N; i++) {
			if(str.charAt(i) == 'A') genome[i] = 0;
			else if(str.charAt(i) == 'G') genome[i] = 1;
			else if(str.charAt(i) == 'C') genome[i] = 2;
			else if(str.charAt(i) == 'T') genome[i] = 3;
			else genome[i] = 4;
		}
		br.close();
		
		long[][][][] dp = new long[N][4][4][4]; // index (i), first letter of 2nd-last substring, (j)
												// first letter of last substring (k), last latter of last substring (l)
		for(int j = 0; j < 4; j++) { // base case - j doesn't matter
			for(int l = 0; l < 4; l++) {
				if(genome[0] == 4 || genome[0] == l) dp[0][j][l][l] = 1;
			}
		}
		
		for(int i = 1; i < N; i++) {
			for(int l = 0; l < 4; l++) { // last letter of last substring (new)
				if(genome[i] != 4 && genome[i] != l) continue;
				
				for(int j = 0; j < 4; j++) { // first letter of 2nd-last substring (new)
					for(int o = 0; o < 4; o++) { // last letter of last substring (old)
						// create new substring
						dp[i][j][l][l] += dp[i-1][o][j][o];
						dp[i][j][l][l] %= MOD;
						
						// extend old substring
						for(int k = 0; k < 4; k++) { // last letter of last substring (old)
							if(o != l) {
								dp[i][j][k][l] += dp[i-1][j][k][o];
								dp[i][j][k][l] %= MOD;
							}
						}
					}
				}
			}
				
		}
		
		long res = 0;
		for(int l = 0; l < 4; l++) {
			for(int k = 0; k < 4; k++) {
				res += dp[N-1][l][k][l];
			}
		}
		System.out.println(res % MOD);
	}
}
