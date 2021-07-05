import java.util.*;
import java.io.*;

public class DiceCombinations {
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		br.close();
		
		int[] dp = new int[N+1];
		dp[0] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= Math.min(6, i); j++) {
				dp[i] += dp[i-j];
				dp[i] %= MOD;
			}
		}
		System.out.println(dp[N]);
	}
}
