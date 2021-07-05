// Cow Cash

import java.util.*;
import java.io.*;

public class money {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[V];
		for(int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			coins[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();

		long[] curr = new long[N+1]; //index: amount, value: #ofways
		long[] prev = new long[N+1];
		
		prev[0] = 1;
		for(int i = 0; i < V; i++) { // coin index
			int coin = coins[i];
			for(int j = 0; j <= N; j++) { //sum value
				curr[j] = prev[j];
				if(j >= coin) curr[j] += curr[j - coin];
			}
			prev = Arrays.copyOf(curr, N+1);
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(curr[N]);
		//out.println(result);
		//out.close();
	}

}

