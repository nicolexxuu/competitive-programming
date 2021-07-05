// Subset Sums

import java.util.*;
import java.io.*;

public class subset {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		br.close();
		
		int sum = 0;
		if(N%2==0) sum = (N+1) * (N/2);
		else sum = (N+1) * (N/2) + (N/2) + 1;
		
		if(sum%2 == 1) System.out.println(0);
		else { //subsetsum(N, sum/2)
			
			sum /= 2;
			int [] dp = new int[sum+1];
	
			dp[0] = 1;
			
			for(int i = 1; i < N; i++) {
				for(int j = 0; j <= sum; j++) {
					if(j >= i+1) dp[j] += dp[j-i-1];
				}
			}

			System.out.println(dp[sum]);
				
		}
	}
}

