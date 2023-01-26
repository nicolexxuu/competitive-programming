// Tests for Haybales

import java.util.*;
import java.io.*;

public class tests {
	static long[] X, H;
	static ArrayList<Integer>[] adj;
	static int x;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) adj[i] = new ArrayList<>();
		X = new long[N+1];
		H = new long[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			adj[Integer.parseInt(st.nextToken())].add(i);
		}
		br.close();
		
		x = N;
		dfs(N, 0, -1);
		long K = N+1;
		
		System.out.println(K);
		for(int i = 0; i < N; i++) {
			System.out.println((K) * (H[0] - H[i]) + X[i]);
		}
	}
	
	public static void dfs(int idx, int h, int par) {
		X[idx] = x;
		H[idx] = h;
		
		h++;
		for(int i = adj[idx].size()-1; i >= 0; i--) {
			int to = adj[idx].get(i);
			if(to == par) continue;
			x--;
			dfs(to, h, idx);
		}
	}
}