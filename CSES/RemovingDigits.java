import java.util.*;
import java.io.*;

public class RemovingDigits {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		br.close();
		
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 200000);
		dp[n] = 0;
		
		for(int i = n; i > 0; i--) {
			int a = i;
			while(a > 0) {
				dp[i - a % 10] = Math.min(dp[i - a % 10], dp[i] + 1);
				a /= 10;
			}
		}
		
		System.out.println(dp[0]);
	}
}