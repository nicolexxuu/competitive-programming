// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=897
// "Cow Poetry", 2019 January Gold Division

import java.util.*;
import java.io.*;

public class poetry {
	
	static int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		String file = "poetry";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Word[] words = new Word[N];
		int [] dp = new int[K + 1];
		dp[0] = 1;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			words[i] = new Word(s, c);
		}
		
		HashMap<String, Integer> scheme = new HashMap<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if(scheme.containsKey(s)) scheme.replace(s, scheme.get(s) + 1);
			else scheme.put(s, 1);
		}
		
		int S = scheme.size();
		int[] schemes = new int[S];
		int i = 0;
		for(String s: scheme.keySet()) {
			schemes[i] += scheme.get(s);
			i++;
		}
		
		br.close();

		// dp
		
		int [] rhyme = new int[N];
		
		for(int j = 0; j <= K; j++) {
			
			for(int k = 0; k < N; k++) {
				Word curr = words[k];
				if(j + curr.s > K) continue;
				if(j + curr.s == K) rhyme[curr.c] = (rhyme[curr.c] + dp[j]) % MOD; 
				dp[j + curr.s] = (dp[j + curr.s] + dp[j]) % MOD;
			}
		}
		
		long result = 1;
		// multiply
		for(int s = 0; s < S; s++) {
			if(schemes[s] == 1) {
				result *= dp[K];
			}
			else {
				long sum = 0;
				for(int base = 0; base < N; base++) {
					if(rhyme[base] == 0) continue;
					sum += exp(rhyme[base], schemes[s]);
					sum %= MOD;
				}
				if(sum > 0) result *= sum;
			}
			
			result %= MOD;
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class Word {
		int s, c;
		Word(int s, int c){
			this.s = s;
			this.c = c;
		}
	}
	
	public static long exp (int num, int pow) {
		if(pow == 0) return num;
		if(pow == 1) return num;
		long ans = exp(num, pow/2);
		ans = (ans * ans) % MOD;
		if(pow%2 == 1) ans = (ans * num) % MOD;
		return ans % MOD;
	}
}

