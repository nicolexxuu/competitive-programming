import java.util.*;
import java.io.*;

public class BookShop {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		int[] cost = new int[n];
		int[] pages = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) cost[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) pages[i] = Integer.parseInt(st.nextToken());
		
		br.close();
		
		int[] dp = new int[x+1]; // cost, last book
		
		for(int b = 1; b <= n; b++) 
			for(int i = x; i >= cost[b-1]; i--) dp[i] = Math.max(pages[b-1] + dp[i-cost[b-1]], dp[i]); 
		
		System.out.println(dp[x]);
	}
}