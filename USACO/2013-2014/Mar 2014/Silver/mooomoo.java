// Mooo Moo

import java.util.*;
import java.io.*;

public class mooomoo {
	static HashMap<Integer, Integer> dp = new HashMap<>();
	public static void main(String[] args) throws IOException {
		String file = "mooomoo";
		//BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[] V = new int[B];
		for(int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] cows = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		dp.put(0, 0);
		for(int start = N-2; start >= 0; start--)
			cows[start+1] -=  Math.max(0, cows[start]-1);

		int result = 0;
		for(int i: cows) result += ks(i, V);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static int ks(int n, int[] V) {
		if(n < 0) return 1000000000;
		if(dp.containsKey(n)) return dp.get(n);
		int ret = 1000000000;
		for(int v: V) ret = Math.min(ret, ks(n-v, V) + 1);
		dp.put(n, ret);
		return ret;
	}
}
