// Union-Find Implementation

import java.util.*;
import java.io.*;

public class UnionFind {
	static int N;
	static int[] rt, sz;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		rt = new int[N];
		sz = new int[N];
		init();
		
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			merge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}
		
		br.close();
	}
	
	public static void init() {
		for(int i = 0; i < N; i++) {
			rt[i] = i;
			sz[i] = 1;
		}
	}
	
	public static int root(int a) {
		while(rt[a] != a) {
			rt[a] = rt[rt[a]];
			a = rt[a];
		}
		
		return a;
	}
	
	public static void merge(int a, int b) {
		int r_a = root(a), r_b = root(b);
		if(r_a == r_b) return;
		
		if(sz[r_a] < sz[r_b]) {
			rt[r_a] = r_b;
			sz[r_b] += sz[r_a];
		} else {
			rt[r_b] = r_a;
			sz[r_a] += sz[r_b];
		}
	}
}
