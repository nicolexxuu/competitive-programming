// Charm Bracelet

import java.util.*;
import java.io.*;

public class charm {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] weight = new int[N];
		int[] value = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int[] dp = new int[M+1];
		
		for(int i = 0; i < N; i++) {
			for(int j = M; j >= weight[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
			}
		}
		int result = dp[M];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
